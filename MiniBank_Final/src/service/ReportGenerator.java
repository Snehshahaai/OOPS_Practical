package service;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator {
    public static void generate(Path logsFolder, Path report) throws IOException {
        Files.createDirectories(logsFolder);

        long deposits = 0;
        long withdrawals = 0;
        StringBuilder details = new StringBuilder();

        try (DirectoryStream<Path> files = Files.newDirectoryStream(logsFolder, "*.log")) {
            for (Path file : files) {
                try (BufferedReader br = Files.newBufferedReader(file)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] p = line.trim().split("\\s+");
                        if (p.length >= 3) {
                            long amount = Long.parseLong(p[2]);
                            if (p[0].equalsIgnoreCase("DEPOSIT")) deposits += amount;
                            if (p[0].equalsIgnoreCase("WITHDRAW")) withdrawals += amount;
                        }
                    }
                }

                BasicFileAttributes a =
                        Files.readAttributes(file, BasicFileAttributes.class);

                details.append(file.getFileName())
                        .append(" | size=").append(a.size())
                        .append(" | modified=").append(a.lastModifiedTime())
                        .append("\n");
            }
        }

        String result = "END OF DAY REPORT\n"
                + "Total deposits: " + deposits + "\n"
                + "Total withdrawals: " + withdrawals + "\n"
                + "Net change: " + (deposits - withdrawals) + "\n"
                + details;

        Files.writeString(report, result,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);

        System.out.println(result);
    }
}
