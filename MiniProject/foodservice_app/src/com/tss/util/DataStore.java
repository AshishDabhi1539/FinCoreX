package com.tss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.tss.customer.Customer;

public class DataStore {

    
    public static void writeObject(String filePath, Object object) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
            oos.close();
            System.out.println("Saved: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to save: " + e.getMessage());
        }
    }

   
    public static Object readObject(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) return null;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object obj = ois.readObject();
            ois.close();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Error reading: " + e.getMessage());
            return null;
        }
    }

   
    @SuppressWarnings("unchecked")
    public static Map<String, Customer> loadCustomerData(String filePath) {
        Object obj = readObject(filePath);
        if (obj instanceof Map) {
            return (Map<String, Customer>) obj;
        }
        return new HashMap<>();
    }

   
    public static void saveCustomerData(String filePath, Map<String, Customer> customerMap) {
        writeObject(filePath, customerMap);
    }
}
