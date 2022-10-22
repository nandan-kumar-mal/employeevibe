package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class RemoveEmployee extends JFrame implements ActionListener{
    
    JLabel labelempID;
    Choice cEmpID;
    JButton delete,back;
    
    RemoveEmployee(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        labelempID = new JLabel("Employee ID");
        labelempID.setBounds(50,50,100,30);
        add(labelempID);
        
        cEmpID = new Choice();
        cEmpID.setBounds(200,50,150,30);
        add(cEmpID);
        
        try{
            Conn c = new Conn();
            String query = "select * from employee where";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                cEmpID.add(rs.getString("empID"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50,100,100,30);
        add(labelname);
        
        JLabel lblname = new JLabel();
        lblname.setBounds(200,100,100,30);
        add(lblname);
        
        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50,150,100,30);
        add(labelphone);
        
        JLabel lblphone = new JLabel();
        lblphone.setBounds(200,150,100,30);
        add(lblphone);
        
        JLabel labelemail = new JLabel("Email ID");
        labelemail.setBounds(50,200,100,30);
        add(labelemail);
        
        JLabel lblemail = new JLabel();
        lblemail.setBounds(200,200,100,30);
        add(lblemail);
        
        try{
            Conn c = new Conn();
            String query = "select * from employee where empID ='"+cEmpID.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                lblname.setText(rs.getString("name"));
                lblphone.setText(rs.getString("phone"));
                lblemail.setText(rs.getString("email"));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        cEmpID.addItemListener(new ItemListener(){
            
            public void itemStateChanged(ItemEvent ie){
                try{
                    Conn c = new Conn();
                    String query = "select * from employee where empID ='"+cEmpID.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()){
                        lblname.setText(rs.getString("name"));
                        lblphone.setText(rs.getString("phone"));
                        lblemail.setText(rs.getString("email"));
                
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                    }
        });
        
        delete = new JButton("Delete");
        delete.setBounds(80,300,100,30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.white);
        delete.addActionListener(this);
        add(delete);
        
        back= new JButton("Back");
        back.setBounds(220,300,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350,0,600,400);
        add(image);
        
        setSize(1000,400);
        setLocation(300,150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae ){
        
        if(ae.getSource() == delete){
            
            try{
                Conn  c= new Conn();
                String query = "delete from employee where empID ='"+cEmpID.getSelectedItem()+"'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Employee Information Deleted Successfully!");
                setVisible(false);
                new Home();
            }catch(Exception e){
                e.printStackTrace();
            }
          
        }else{
            setVisible(false);
            new Home();
        }
    }
    public static void main(String args []){
        new RemoveEmployee();
        
    }
}
