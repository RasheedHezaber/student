package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.View.inflate
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class Fragmenttwo :Fragment(), StudentDialog.Callbacks {
    override fun onStudentAdd(student: Student)
    {
        studentListViewModel.addStudent(student)
        update()

    }

    override fun onStudentDelete(position: Int)
    {
        studentListViewModel.deleteStudent(position)
        update()

    }
    private lateinit var studentRecyclerView: RecyclerView
    private var adapter: StudentAdapter? = null
    private lateinit var nodata: TextView
    private val studentListViewModel: StudentViweModel by lazy {
        ViewModelProviders.of(this).get(StudentViweModel::class.java)
    }
    lateinit var button1: Button
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragmenttwo,container,false)
        studentRecyclerView=view.findViewById(R.id.stu_recycler_view) as RecyclerView
        studentRecyclerView.layoutManager=LinearLayoutManager(context)
        nodata = view.findViewById(R.id.empty_list_tv) as TextView
        update()
        return view

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_blank, menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return when (item.itemId) {
            R.id.new_student -> {
                StudentDialog.newInstance().apply {
                    setTargetFragment(this@Fragmenttwo,0)
                    show(this@Fragmenttwo.requireFragmentManager(),"Input")
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    private inner class StudentViweHolder(view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        private lateinit var student: Student
        private val name: TextView = itemView.findViewById(R.id.stu_name)
        private val number: TextView = itemView.findViewById(R.id.stu_number)
        val passText: TextView = itemView.findViewById(R.id.pass)

        var deleteBtn : Button = itemView.findViewById(R.id.delete)


        init {
            deleteBtn.setOnClickListener(this)
        }
         fun bind(student: Student) {

            this.student = student
            name.text=this.student.name
            number.text=this.student.number
             passText.text = this.student.pass.toString()
        }

        override fun onClick(v: View?) {
            onStudentDelete(adapterPosition)
        }
    }
    private inner class StudentAdapter(var students: List<Student>): RecyclerView.Adapter<StudentViweHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViweHolder {
            val view = layoutInflater.inflate(R.layout.list_item, parent, false)
            return StudentViweHolder(view)
        }

        override fun getItemCount(): Int {
            if (students.isEmpty())
                nodata.visibility=View.VISIBLE
            else
                nodata.visibility= View.GONE
            return students.size
        }

        override fun onBindViewHolder(holder: StudentViweHolder, position: Int) {
            val stud = students[position]
            holder.bind(stud)
        }


    }
    fun update(){
        val student = studentListViewModel.students
        adapter = StudentAdapter(student)
        studentRecyclerView.adapter = adapter
    }
}


