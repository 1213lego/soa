package bikeproject.api;

import bikeproject.api.model.Bike;
import bikeproject.api.model.BikeList;
import bikeproject.api.model.BikeResponse;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
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
            bikeList = bikeListResponse.body().getBikeList();
        }
        catch (Exception e){
            System.out.println("No hay conexion con el servidor: getBikes()");
        }
        return bikeList;
    }
    public BikeResponse saveBike(Bike bike){
        BikeResponse bikeResponse = null;
        try{
            Call<BikeResponse> call = bikeServiceRetrofit.saveBike(bike);
            Response<BikeResponse> response = call.execute();
            if (response.isSuccessful()){
                bikeResponse = response.body();
                System.out.println(bikeResponse.getItem());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bikeResponse;
    }
}
