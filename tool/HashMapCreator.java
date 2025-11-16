//package net.murat.nodict;

import java.io.*;
import java.util.HashMap;

// Custom
import net.murat.nodict.TRNOWord;

// PC'de çalıştır, hashmap.dat oluştursun
public class HashMapCreator {
    public static void main(String[] args) {
        HashMap<String, TRNOWord> wordMap = new HashMap<>();
        
        // words.txt'den oku
        try (BufferedReader reader = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String tr = parts[0].trim();
                    String no = parts[1].trim();
                    TRNOWord word = new TRNOWord(tr, no);
                    wordMap.put(tr, word);
                    wordMap.put(no, word);
                }
            }
            
            // HashMap'i kaydet
            FileOutputStream fos = new FileOutputStream("hashmap.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(wordMap);
            oos.close();
            
            System.out.println("HashMap başarıyla oluşturuldu! Toplam kelime: " + wordMap.size() / 2);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
