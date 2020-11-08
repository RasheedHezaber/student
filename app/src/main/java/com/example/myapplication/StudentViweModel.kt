package com.example.myapplication

import androidx.lifecycle.ViewModel

class StudentViweModel:ViewModel() {
    val students = mutableListOf<Student>()

  init {
      for (i in 0 until 10) {
          var stu = Student()
          stu.name = "rasheed#$i"
          stu.number="771011888#$i"
           students += stu

  }
}
    fun addStudent(student: Student)
    {
        students.add(student)
    }
    fun deleteStudent(stu: Int) {
        students.removeAt(stu)

    }
}

