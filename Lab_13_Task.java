package LabTasks;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;

public class Lab_13_Task {
    static Connection connection=null;
    JFrame frame;
    JPanel panel;
    JLabel name,roll,batch,section,gender,qualification,address,country;
    JTextField t1,t2,t3,t4;
    JTextArea textArea;
    JCheckBox c1,c2,c3,c4;
    String[] countryList={"Pakistan","India","Australia","England","Sri Lanka","Bangladesh","New Zealand"};
    JComboBox<String> comboBox=new JComboBox<>(countryList);
    JRadioButton r1,r2;
    JButton b1,b2,b3,b4;
    ButtonGroup buttonGroup1,buttonGroup2;
    JSONObject jsonObject=new JSONObject();

    Lab_13_Task()
    {
        frame=new JFrame("Student Registration");
        panel=new JPanel();
        name=new JLabel("Name: ");
        roll=new JLabel("Roll Number: ");
        batch=new JLabel("Batch: ");
        section=new JLabel("Section: ");
        gender=new JLabel("Gender: ");
        qualification=new JLabel("Qualification");
        address=new JLabel("Address");
        country=new JLabel("Country");
        c1=new JCheckBox("Matric");
        c2=new JCheckBox("Intermediate");
        c3=new JCheckBox("Graduate");
        c4=new JCheckBox("Post Graduate");
        buttonGroup1=new ButtonGroup();
        buttonGroup2=new ButtonGroup();
        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();
        t4=new JTextField();
        textArea=new JTextArea();
        r1=new JRadioButton("Male");
        r2=new JRadioButton("Female");
        b1=new JButton("Save");
        b2=new JButton("Print");
        b3=new JButton("Database");
        b4=new JButton("Show Database");
        panel.setLayout(null);
        frame.setSize(700,700);
        panel.setSize(700,700);
        panel.setBackground(Color.yellow);
        name.setBounds(50,50,100,50);
        roll.setBounds(50,100,100,50);
        batch.setBounds(50,150,100,50);
        section.setBounds(50,200,100,50);
        gender.setBounds(50,250,100,50);
        qualification.setBounds(50,300,100,50);
        address.setBounds(50,400,100,50);
        country.setBounds(50,490,100,50);
        c1.setBounds(180,310,100,30);
        c2.setBounds(330,310,100,30);
        c3.setBounds(180,360,100,30);
        c4.setBounds(330,360,120,30);
        t1.setBounds(180,60,150,30);
        t2.setBounds(180,110,150,30);
        t3.setBounds(180,160,150,30);
        t4.setBounds(180,210,150,30);
        textArea.setBounds(180,400,220,75);
        comboBox.setBounds(180,500,200,30);
        comboBox.setEditable(false);
        r1.setBounds(180,260,70,30);
        r2.setBounds(280,260,70,30);
        b1.setBounds(50,600,70,30);
        b2.setBounds(140,600,70,30);
        b3.setBounds(230,600,70,30);
        b4.setBounds(320,600,130,30);
        buttonGroup1.add(r1);
        buttonGroup1.add(r2);
        buttonGroup2.add(c1);
        buttonGroup2.add(c2);
        buttonGroup2.add(c3);
        buttonGroup2.add(c4);
        panel.add(name);
        panel.add(roll);
        panel.add(batch);
        panel.add(section);
        panel.add(gender);
        panel.add(qualification);
        panel.add(address);
        panel.add(country);
        panel.add(c1);
        panel.add(c2);
        panel.add(c3);
        panel.add(c4);
        panel.add(t1);
        panel.add(t2);
        panel.add(t3);
        panel.add(t4);
        panel.add(textArea);
        panel.add(comboBox);
        panel.add(r1);
        panel.add(r2);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showData();
            }
        });
        b3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                database();
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDatabase();
                frame.dispose();
            }
        });

    }
    public void saveData()
    {

        String gender;
        String qualification;
        if(c1.isSelected())qualification="Matric";
        else if(c2.isSelected())qualification="Intermediate";
        else if(c3.isSelected())qualification="Graduate";
        else qualification="Post Graduate";
        if(r1.isSelected())gender="Male";
        else gender="Female";
        String name=t1.getText();
        String rollNumber=t2.getText();
        String batch=t3.getText();
        String section=t4.getText();
        String country=(String)comboBox.getSelectedItem();
        String address=textArea.getText();
        jsonObject.put("Name",name);
        jsonObject.put("RollNumber",rollNumber);
        jsonObject.put("Batch",batch);
        jsonObject.put("Section",section);
        jsonObject.put("Gender",gender);
        jsonObject.put("Qualification",qualification);
        jsonObject.put("Country",country);
        jsonObject.put("Address",address);
        try {
            FileWriter file=new FileWriter("MyFile.json");
            file.write(jsonObject.toJSONString());
            file.close();
            JOptionPane.showMessageDialog(null,"Data stored successfully.");
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    public void showData()
    {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("MyFile.json"));
            JSONObject object = (JSONObject) obj;
            String name = (String) object.get("Name");
            String rollNumber = (String) object.get("RollNumber");
            String batch = (String) object.get("Batch");
            String section = (String) object.get("Section");
            String gender = (String) object.get("Gender");
            String qualification = (String) object.get("Qualification");
            String country = (String) object.get("Country");
            String address = (String) object.get("Address");
            frame.remove(panel);
            Panel panel1 = new Panel();
            panel1.setLayout(null);
            panel1.setBackground(Color.orange);
            panel1.setSize(700, 700);
            JLabel l1, l2, l3, l4, l5, l6, l7, l8;
            l1 = new JLabel("Name : " + name);
            l2 = new JLabel("Roll #: " + rollNumber);
            l3 = new JLabel("Batch: " + batch);
            l4 = new JLabel("Section: " + section);
            l5 = new JLabel("Gender: " + gender);
            l6 = new JLabel("Qualification: " + qualification);
            l7 = new JLabel("Country: " + country);
            l8 = new JLabel("Address: " + address);
            l1.setBounds(10, 20, 200, 50);
            l2.setBounds(10, 70, 200, 50);
            l3.setBounds(10, 120, 200, 50);
            l4.setBounds(10, 170, 200, 50);
            l5.setBounds(10, 220, 200, 50);
            l6.setBounds(10, 270, 200, 50);
            l7.setBounds(10, 320, 200, 50);
            l8.setBounds(10, 370, 200, 50);
            panel1.add(l1);
            panel1.add(l2);
            panel1.add(l3);
            panel1.add(l4);
            panel1.add(l5);
            panel1.add(l6);
            panel1.add(l7);
            panel1.add(l8);
            frame.add(panel1);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }
    public void database(){
        try{
            String query="insert into students(Name,RollNumber,Batch,Section,Gender,Qualification,Country,Address) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps=connection.prepareStatement(query);
            String gender;
            if(r1.isSelected())gender="Male";
            else gender="Female";
            String qualify;
            if(c1.isSelected())qualify="Matric";
            else if(c2.isSelected())qualify="Intermediate";
            else if(c3.isSelected())qualify="Graduation";
            else qualify="Post Graduate";

            ps.setString(1,t1.getText());
            ps.setString(2,t2.getText());
            ps.setString(3,t3.getText());
            ps.setString(4,t4.getText());
            ps.setString(5,gender);
            ps.setString(6,qualify);
            ps.setString(8,textArea.getText());
            ps.setString(7,comboBox.getSelectedItem().toString());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data stored successfully");
//                frame.dispose();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            System.exit(0);
        }
    }
    public void showDatabase(){
        String Name=JOptionPane.showInputDialog(null,"Enter name");
        try{
            Statement st=connection.createStatement();
            String query="select * from students where Name = '"+Name+"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                String name=rs.getString("Name");
                String rollNumber=rs.getString("RollNumber");
                String batch=rs.getString("Batch");
                String section=rs.getString("Section");
                String gender=rs.getString("Gender");
                String qualification=rs.getString("Qualification");
                String address=rs.getString("Address");
                String country=rs.getString("Country");
                JFrame frame1=new JFrame("Student Registration");
                JPanel panel1 = new JPanel(null);
//                    panel1.setLayout(null);
                frame1.setSize(700,700);
                panel1.setBackground(Color.orange);
                panel1.setSize(700, 700);
                JLabel l1, l2, l3, l4, l5, l6, l7, l8;
                l1 = new JLabel("Name : " + name);
                l2 = new JLabel("Roll #: " + rollNumber);
                l3 = new JLabel("Batch: " + batch);
                l4 = new JLabel("Section: " + section);
                l5 = new JLabel("Gender: " + gender);
                l6 = new JLabel("Qualification: " + qualification);
                l7 = new JLabel("Country: " + country);
                l8 = new JLabel("Address: " + address);
                l1.setBounds(10, 20, 200, 50);
                l2.setBounds(10, 70, 200, 50);
                l3.setBounds(10, 120, 200, 50);
                l4.setBounds(10, 170, 200, 50);
                l5.setBounds(10, 220, 200, 50);
                l6.setBounds(10, 270, 200, 50);
                l7.setBounds(10, 320, 200, 50);
                l8.setBounds(10, 370, 200, 50);
                panel1.add(l1);
                panel1.add(l2);
                panel1.add(l3);
                panel1.add(l4);
                panel1.add(l5);
                panel1.add(l6);
                panel1.add(l7);
                panel1.add(l8);
                frame1.add(panel1);
                frame1.setVisible(true);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "arfa0331"); // securing Password for safety purpose
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
           // System.exit(0);
        }

        Lab_13_Task gui=new Lab_13_Task();
    }
}