package com.example.easmuslifeapp

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Window
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.easmuslifeapp.databinding.ActivityUserDataBinding
import com.example.easmuslifeapp.datamodels.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class UserDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDataBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dataBaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri
    private lateinit var dialog: Dialog
    private lateinit var profileImage: de.hdodenhof.circleimageview.CircleImageView
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        dataBaseReference = FirebaseDatabase.getInstance().getReference("Users")

        profileImage = findViewById(R.id.profile_image)
        profileImage.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            resultLauncher.launch(cameraIntent)
        }

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    handleCameraImage(result.data)
                }
            }
        binding.saveBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
//            showProgressBar()
//            val firstName = binding.etFirstName.text.toString()
//            val lastName = binding.etLastName.text.toString()
//            dataBaseReference.child("Darco").setValue("1")
//            val user = User(firstName, lastName)
//            if (uid != null) {
//                dataBaseReference.child(uid).setValue(user).addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        uploadProfilePicture()
//                    } else {
//                        hideProgressBar()
//                        Toast.makeText(this, "Failed to update Profile", Toast.LENGTH_LONG).show()
//                    }
//                }
//            }
        }
    }

    private fun handleCameraImage(intent: Intent?) {
        val bitmap = intent?.extras?.get("data") as Bitmap
        profileImage.setImageBitmap(bitmap)
    }

    private fun uploadProfilePicture() {
        imageUri = Uri.parse("android.resource://$packageName/${R.drawable.profile}")
        storageReference =
            FirebaseStorage.getInstance().getReference("Users/" + auth.currentUser?.uid)
        storageReference.putFile(imageUri).addOnCompleteListener {
            hideProgressBar()
            Toast.makeText(this, "Uploaded image successfully", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            hideProgressBar()
            Toast.makeText(this, "Failed to upload image", Toast.LENGTH_LONG).show()
        }
    }

    private fun showProgressBar(){
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun hideProgressBar(){
        dialog.dismiss()
    }

}