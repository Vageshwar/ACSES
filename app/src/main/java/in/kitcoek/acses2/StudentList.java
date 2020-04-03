package in.kitcoek.acses2;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StudentList extends ArrayAdapter<Student> {
    private Activity context;
    private List<Student> studentList;

    public StudentList(Activity context, List<Student> studentList) {
        super(context,R.layout.list_layout,studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView name = listViewItem.findViewById(R.id.name);
        TextView serialNo = listViewItem.findViewById(R.id.serialNo);
        TextView mobile = listViewItem.findViewById(R.id.mobile);
        Student student = studentList.get(position);
        name.setText(student.getName());
        serialNo.setText(student.getSerialNo());
        mobile.setText(student.getCollege());
        return listViewItem;
    }
}
