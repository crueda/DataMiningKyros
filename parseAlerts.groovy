import groovy.sql.Sql
import java.util.*;
import java.text.*;

def evento = 'PANIC'
def vehiculo = "OCU_BEL01"
def fecha

archivo = new File("panicos.txt")

def sql = Sql.newInstance("jdbc:mysql://172.26.0.3:3306/kyros4f1", "root",
                      "", "com.mysql.jdbc.Driver")

sql.eachRow("select * from VEHICLE_EVENT where TYPE_EVENT=${evento} and VEHICLE_LICENSE=${vehiculo}") {
    //println "-> ${it.DATE_EVENT}"

    fecha = it.DATE_EVENT

	Date resultdate = new Date(fecha);
	
	//SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd,yyyy HH:mm");
	//System.out.println(sdf1.format(resultdate)); 

	SimpleDateFormat sdf2 = new SimpleDateFormat("k,W,w,M,1");
    //System.out.println(sdf2.format(resultdate)); 
    archivo.append(sdf2.format(resultdate))
    archivo.append("\n")
	
}

