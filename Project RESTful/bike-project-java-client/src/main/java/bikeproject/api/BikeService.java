package bikeproject.api;

import bikeproject.api.model.Bike;
import bikeproject.api.model.BikeList;
import bikeproject.api.model.BikeResponse;
import bikeproject.api.model.BikerErrorReponse;
import okhttp3.ResponseBody;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import retrofit2.Call;
import retrofit2.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BikeService {
   private ApiClient.BikeServiceRetrofit bikeServiceRetrofit;
    public BikeService(){
        bikeServiceRetrofit = ApiClient.getBikeService();
    }
    public List<Bike> getBikes() {
        List<Bike> bikeList = new ArrayList<>();
        try {
            Call<BikeList> bikeListCall = bikeServiceRetrofit.getBikes();
            Response<BikeList> bikeListResponse = bikeListCall.execute();
            if(bikeListResponse.isSuccessful()){
                bikeList = bikeListResponse.body().getBikeList();
            }

        }
        catch (Exception e){
            System.out.println("No hay conexion con el servidor: getBikes()");
        }
        return bikeList;
    }
    public BikeResponse saveBike(Bike bike){
        BikeResponse bikeResponse = null;
        try{

            Call<BikeResponse> call = bikeServiceRetrofit.saveBike(new Bike("ssss", Bike.Type.GRAVEL,"sdd",233,4,new Date()));
            Response<BikeResponse> response = call.execute();
            if (response.isSuccessful()){
                bikeResponse = response.body();
                System.out.println(bikeResponse.getItem());
                System.out.println(bikeResponse.getMessage());
            }
            else {
                System.out.println("Error body");
                /*Serializer serializer = new Persister();
                BikerErrorReponse bikerErrorReponse = serializer.read(BikerErrorReponse.class,response.errorBody().byteStream());*/
                ResponseBody errorrResponse = response.errorBody();
                BufferedReader bf = new BufferedReader(new InputStreamReader(errorrResponse.byteStream()));
                bf.lines().forEach((s -> System.out.println(s)));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bikeResponse;
    }
}
