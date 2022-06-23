package id.ac.uvers.ewash;

import android.net.Uri;

public class urlcrud {

    public static final String tandaBaca = "@#&=*+-_.,:!?()/~'%";

    public static String insertCUser (String username,String password,String nama, String nohp, String alamat ){
        return Uri.encode("https://ewash22.000webhostapp.com/databaseewash/simpan.php?username="+username+"&nama="+nama+"&password="+password+"&nohp="+nohp+"&alamat="+alamat, tandaBaca);
    }

    public static String selectCUser (String username,String password){
        return Uri.encode("https://ewash22.000webhostapp.com/databaseewash/selectuser.php?username="+username+"&password="+password, tandaBaca);
    }

    public static String selectLaundryData(){
        return Uri.encode("https://ewash22.000webhostapp.com/databaseewash/selectlaundry.php?");
    }

    public static String insertFinalorder (String id_laundrydata,String user,String namalaundry, String tgltrans, String bajuharian, String boneka, String bedcover, String selimutb, String selimutk, String gorden,String totaltrans, String deliveryterm, String alamat, String kategori, String service, String payment){
        return Uri.encode("https://ewash22.000webhostapp.com/databaseewash/simpan.php?id_laundrydata="+id_laundrydata+"&user="+user+"&namalaundry="+namalaundry+"&tgltrans="+tgltrans+"&bajuharian="+bajuharian+"&boneka="+boneka+"&bedcover="+bedcover+"&selimutb="+selimutb+"&selimutk="+selimutk+"&gorden="+gorden+"&totaltrans="+totaltrans+"&deliveryterm="+deliveryterm+"&alamat="+alamat+"&kategori="+kategori+"&service="+service+"&payment="+payment, tandaBaca);
    }

}
