package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;


public class ViewEmployees extends JFrame implements ActionListener{
    
    JTable table;
    Choice cemployeeID;
    JButton search,print,update,back;
    
    
    ViewEmployees(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel searchlbl = new JLabel("Search by Employee ID");
        searchlbl.setBounds(20,20,150,20);
        add(searchlbl);
        
        cemployeeID = new Choice();
        cemployeeID.setBounds(180,20,150,20);
        add(cemployeeID);
        
        
        table = new JTable();
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
            while(rs.next()){
                cemployeeID.add(rs.getString("empID"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,100,900,600);
        add(jsp);
        
        search = new JButton ("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton ("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);
        
        update = new JButton ("Update");
        update.setBounds(220,70,80,20);
        update.addActionListener(this);
        add(update);
        
        back = new JButton ("Back");
        back.setBounds(320,70,80,20);
        back.addActionListener(this);
        add(back);
        
        
        setSize(900,700);
        setLocation(300,100);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()== search){
            
            String query ="select * from employee where empID = '"+cemployeeID.getSelectedItem()+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs)); 
            }catch(Exception e){
                e.printStackTrace();
                
            }
        }else if(ae.getSource() == print){
            try{
                table.print();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()== update){
            setVisible(false);
            new UpdateEmployee(cemployeeID.getSelectedItem());
        }else{
            setVisible(false);
            new Home();
        }
    }
    public static void main(String args[]){
        new ViewEmployees();
    }
}
