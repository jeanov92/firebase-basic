package com.firebase.example;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.List;

public class ReadData {

    public static void initialize() {
        // asynchronously retrieve all users
        try {
            FirestoreOptions firestoreOptions =
                    FirestoreOptions.getDefaultInstance().toBuilder()
                            .setProjectId("java-project-201910")
                            .build();
            Firestore db = firestoreOptions.getService();
            ApiFuture<QuerySnapshot> query = db.collection("users").get();

            // query.get() blocks on response
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                System.out.println("User: " + document.getId());
                System.out.println("First: " + document.getString("first"));
                if (document.contains("middle")) {
                    System.out.println("Middle: " + document.getString("middle"));
                }
                System.out.println("Last: " + document.getString("last"));
                System.out.println("Born: " + document.getLong("born"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
