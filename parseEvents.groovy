import groovy.sql.Sql
import java.util.*;
import java.text.*;

def vehiculo = "lavadora3"
def fecha
def evento

archivo = new File("eventos.arff")
archivo.write("@relation panicos\n");

archivo.append("\n");
archivo.append("@attribute Hora Integer\n");
archivo.append("@attribute Dia Integer\n");
archivo.append("@attribute Semana Integer\n");
archivo.append("@attribute Mes Integer\n");
archivo.append("@attribute Evento {VEHICLE_STARTED,VEHICLE_STOPPED,LOW_BATTERY,PANIC}\n");

archivo.append("\n");
archivo.append("@data\n");


def sql = Sql.newInstance("jdbc:mysql://172.26.0.3:3306/kyros4f1", "root",
                      "", "com.mysql.jdbc.Driver")

sql.eachRow("select * from VEHICLE_EVENT where VEHICLE_LICENSE=${vehiculo}") {
    //println "-> ${it.DATE_EVENT}"

    fecha = it.DATE_EVENT
    evento = it.TYPE_EVENT

	Date resultdate = new Date(fecha);
	
	//SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd,yyyy HH:mm");
	//System.out.println(sdf1.format(resultdate)); 

	SimpleDateFormat sdf2 = new SimpleDateFormat("k,W,w,M");
    //System.out.println(sdf2.format(resultdate)); 
    archivo.append(sdf2.format(resultdate))
    archivo.append(",${evento}\n")
	
}

