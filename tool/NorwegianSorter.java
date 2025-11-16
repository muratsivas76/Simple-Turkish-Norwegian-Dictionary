import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.text.Collator;

public class NorwegianSorter {
    
    public static void main(String[] args) {
        String inputFile = "words.txt";
        String outputFile = "sorted_words.txt";
        
        try {
            sortNorwegianFile(inputFile, outputFile);
            System.out.println("Dosya Norveççe alfabe sırasına göre sıralandı: " + outputFile);
        } catch (IOException e) {
            System.err.println("Hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void sortNorwegianFile(String inputFilePath, String outputFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFilePath), StandardCharsets.UTF_8);
        
        // Norveççe locale için Collator
        Collator norwegianCollator = Collator.getInstance(new Locale("no", "NO"));
        norwegianCollator.setStrength(Collator.PRIMARY); // Büyük/küçük harf farkını ignore et
        
        // Özel Comparator: lowercase'e göre sırala ama orijinal case'i koru
        Comparator<String> norwegianComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Sadece Norveççe kısımları al (":" öncesi)
                String norwegian1 = extractNorwegianPart(s1);
                String norwegian2 = extractNorwegianPart(s2);
                
                // Lowercase'e çevirip karşılaştır
                return norwegianCollator.compare(
                    norwegian1.toLowerCase(new Locale("no", "NO")),
                    norwegian2.toLowerCase(new Locale("no", "NO"))
                );
            }
            
            private String extractNorwegianPart(String line) {
                if (line.contains(":")) {
                    return line.split(":")[0].trim();
                }
                return line.trim();
            }
        };
        
        // Satırları Norveççe sıralamasına göre sırala
        Collections.sort(lines, norwegianComparator);
        
        // Sıralanmış satırları yeni dosyaya yaz
        Files.write(Paths.get(outputFilePath), lines, 
                   StandardCharsets.UTF_8, 
                   StandardOpenOption.CREATE, 
                   StandardOpenOption.TRUNCATE_EXISTING);
    }
    
}
