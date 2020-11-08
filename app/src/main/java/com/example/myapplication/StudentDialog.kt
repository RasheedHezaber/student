package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import java.util.*

class StudentDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val v = activity?.layoutInflater?.inflate(R.layout.dialog,null)
        val noEditText = v?.findViewById(R.id.no_student) as EditText
        val nameEditText = v?.findViewById(R.id.name_student) as EditText
        val passCheckBox = v?.findViewById(R.id.pass_student) as CheckBox
        return AlertDialog.Builder(requireContext(),R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setView(v)
            .setPositiveButton("add") { dialog, _ ->
                val s = Student(
                    UUID.randomUUID(),
                    nameEditText.text.toString(),
                    noEditText.text.toString(),
                    passCheckBox.isChecked
                )
                targetFragment?.let {
                    (it as Callbacks).onStudentAdd(s)
                }

            }
            .setNegativeButton("cancel") { dialog, _ ->
                dialog.cancel()

            }.create()
}
    interface Callbacks {
        fun onStudentAdd(student: Student)
        fun onStudentDelete(position: Int)
    }

    companion object {
        fun newInstance(): StudentDialog {

            return StudentDialog()
        }
    }
}