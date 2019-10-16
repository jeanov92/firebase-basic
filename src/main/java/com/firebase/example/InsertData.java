package com.firebase.example;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;

import java.util.HashMap;
import java.util.Map;

public class InsertData {

    public static void initialize() {
        try {
            System.out.println("Welcome to Firestore configuration");
            FirestoreOptions firestoreOptions =
                    FirestoreOptions.getDefaultInstance().toBuilder()
                            .setProjectId("java-project-201910")
                            .build();
            Firestore db = firestoreOptions.getService();
            DocumentReference docRef = db.collection("users").document("alovelace");
            // Add document data  with id "alovelace" using a hashmap
            Map<String, Object> data = new HashMap<>();
            data.put("first", "Ada");
            data.put("last", "Lovelace");
            data.put("born", 1819);
            //asynchronously write data
            ApiFuture<WriteResult> result = docRef.set(data);
            // result.get() blocks on response
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
