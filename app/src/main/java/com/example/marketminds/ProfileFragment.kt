package com.example.marketminds

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.marketminds.logins.LoginPage
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize FirebaseAuth instance
        auth = FirebaseAuth.getInstance()

        // Find logout button
        val logoutButton = view.findViewById<Button>(R.id.btn_logout)

        // Set onClickListener for the logout button
        logoutButton.setOnClickListener {
            // Sign out from Firebase
            auth.signOut()

            // Show a toast message
            Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()

            // Redirect to the LoginPage activity
            val intent = Intent(requireContext(), LoginPage::class.java)
            startActivity(intent)

            // Close the current activity
            requireActivity().finish()
        }
    }
}
