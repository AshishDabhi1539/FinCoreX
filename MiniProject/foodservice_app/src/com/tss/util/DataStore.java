package com.tss.util;

import java.io.*;

public class DataStore {

    public static <T> void saveToFile(T object, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(object);
        } catch (IOException e) {
            System.out.println("[Error] Saving file failed: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T readFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[Error] Reading file failed: " + e.getMessage());
            return null;
        }
    }

    public static void appendToFile(Object object, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename, true);
             AppendingObjectOutputStream oos = new AppendingObjectOutputStream(fos)) {
            oos.writeObject(object);
        } catch (IOException e) {
            System.out.println("[Error] Appending to file failed: " + e.getMessage());
        }
    }

    private static class AppendingObjectOutputStream extends ObjectOutputStream {
        public AppendingObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }
        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
    
    
}
