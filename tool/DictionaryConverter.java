import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class DictionaryConverter {
    
    public static void main(String[] args) {
        String inputFile = "nwords_01.txt";
        String outputFile = "converted_xyz.txt";
        
        try {
            convertDictionaryFile(inputFile, outputFile);
            System.out.println("Dosya başarıyla dönüştürüldü: " + outputFile);
        } catch (IOException e) {
            System.err.println("Hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void convertDictionaryFile(String inputFilePath, String outputFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFilePath), StandardCharsets.UTF_8);
        List<String> convertedLines = new ArrayList<>();
        
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                convertedLines.add("");
                continue;
            }
            
            // "TR : NO" formatını "NO : TR" formatına dönüştür
            String[] parts = line.split("\\s*:\\s*", 2);
            if (parts.length == 2) {
                String turkish = parts[0].trim();
                String norwegian = parts[1].trim();
                convertedLines.add(norwegian + ":" + turkish);
            } else {
                // Format uygun değilse olduğu gibi ekle
                convertedLines.add(line);
            }
        }
        
        // Dönüştürülmüş satırları yeni dosyaya yaz
        Files.write(Paths.get(outputFilePath), convertedLines, 
                   StandardCharsets.UTF_8, 
                   StandardOpenOption.CREATE, 
                   StandardOpenOption.TRUNCATE_EXISTING);
    }
    
}
