package com.base.myweb.controller;

import java.sql.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Bim")
public class BimController {

    @RequestMapping("/index")
    public String index( Model model){

        return "Bim/index";
    }

    @ResponseBody
    @RequestMapping("getTreeJson")
    public JSONArray getTreeJson(){
        JSONArray retArr = new JSONArray();
        //root
        String sql = "select entity_id,        \n" +
                "        (\n" +
                "        select \n" +
                "                (select value from _objects_val where id = t.value_id )  \n" +
                "        from _objects_eav t \n" +
                "        where t.entity_id = _objects_eav.entity_id \n" +
                "        and  (select name from _objects_attr where id =t.attribute_id ) ='name'\n" +
                "      ) name,\n" +
                "       (\n" +
                "        select \n" +
                "              count(1)\n" +
                "        from _objects_eav t \n" +
                "        where t.entity_id = _objects_eav.entity_id \n" +
                "        and  (select name from _objects_attr where id =t.attribute_id ) ='child'\n" +
                "      ) child\n" +
                "      \n" +
                " from _objects_eav \n" +
                " where \n" +
                "       entity_id not in (\n" +
                "                 select entity_id  \n" +
                "                 from _objects_eav,_objects_attr\n" +
                "                 where _objects_attr.id = _objects_eav.attribute_id\n" +
                "                       and _objects_attr.name ='parent'\n" +
                " )\n" +
                " and attribute_id = (select id from _objects_attr where name= 'name')";
        retArr = getDataFromDb(sql);
        return retArr;
    }

    public JSONArray getChildNode(String pId){
       String sql = "select entity_id,        \n" +
               "        (\n" +
               "        select \n" +
               "                (select value from _objects_val where id = t.value_id )  \n" +
               "        from _objects_eav t \n" +
               "        where t.entity_id = _objects_eav.entity_id \n" +
               "        and  (select name from _objects_attr where id =t.attribute_id ) ='name'\n" +
               "      ) name,\n" +
               "       (\n" +
               "        select \n" +
               "              count(1)\n" +
               "        from _objects_eav t \n" +
               "        where t.entity_id = _objects_eav.entity_id \n" +
               "        and  (select name from _objects_attr where id =t.attribute_id ) ='child'\n" +
               "      ) child\n" +
               "      \n" +
               " from _objects_eav \n" +
               " where \n" +
               "       entity_id in (\n" +
               "                select entity_id  \n" +
               "   from _objects_eav,_objects_attr\n" +
               "   where _objects_attr.id = _objects_eav.attribute_id\n" +
               "         and _objects_attr.name ='parent'\n" +
               " and (select value from _objects_val where id = _objects_eav.value_id )  =" + pId +
               " )\n" +
               " and attribute_id = (select id from _objects_attr where name= 'name')";
        return getDataFromDb(sql);
    }

    public JSONArray getDataFromDb(String sql){
        JSONArray arr = new JSONArray();
        Connection c = null;
        Statement stmt = null;
        String db = "C:\\Users\\Administrator\\Desktop\\工作\\BIM\\apache-tomcat-bim\\webapps\\ROOT\\output\\model.sdb";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+db);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                JSONObject tmpObj = new JSONObject();
                String id = rs.getString("entity_id");
                tmpObj.put("id",id);
                tmpObj.put("name",rs.getString("name"));
                int cnum = rs.getInt("child");
                if(cnum>0){
                    tmpObj.put("isParent",true);
                    tmpObj.put("children",getChildNode(id));
                }
                tmpObj.put("open",true);
                arr.add(tmpObj);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    return arr;
    }

}
