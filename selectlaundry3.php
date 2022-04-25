<?php 
include 'koneksi.php';

$respon = array();

$perintah = "SELECT * FROM laundrydata";
$eksekusi = mysqli_query($connect_db, $perintah);
$cek = mysqli_affected_rows($connect_db);

    if($cek > 0){
        $response["id_laundry"] = 1;
        $response["msg"]= "ADA DATA";
        $response["laundrylists"] = array();


        $server_name = $_SERVER['SERVER_ADDR'];
        
        while($ambil = mysqli_fetch_object($eksekusi)){
            $n["id_laundry"] = $ambil->id_laundry;
            $n["namalaundry"] = $ambil->namalaundry;
            $n["jambuka"] = $ambil->jambuka;
            $n["jamtutup"] = $ambil->jamtutup;
            $n["rating"] = $ambil->rating;
            
           //$response = $n;
           array_push($response["laundrylists"], $n);
           //array_push($n);
        }
    }
    else{
        $response["id_laundry"] = 0;
        $response["msg"]= "TIDAK ADA DATA";
    }

    
    
    
    echo json_encode($response);
    mysqli_close($connect_db);
    ?>