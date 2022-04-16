package controller;

import model.Room;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MyTable {
    JFrame f;

    String[] convertObjectToStr(Room room){
        String[] results = new String[2];
        results[0] = String.valueOf(room.getId());
        results[1] = room.getName();
        return results;
    }

    MyTable(){
        f=new JFrame();

        List<Room> list = new ArrayList<>();
        list.add(new Room().setId(1).setName("TC201"));
        list.add(new Room().setId(2).setName("TC202"));

        String data[][]= new String[list.size()][];

        for(int i=0;i<list.size(); i++){
            data[i] = convertObjectToStr(list.get(i));
        }

        String column[]={"ID","NAME"};

        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);

        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);

        f.setSize(300,400);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new MyTable();
    }
}