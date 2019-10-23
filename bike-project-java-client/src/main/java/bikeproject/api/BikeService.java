package bikeproject.api;

import bikeproject.api.model.Bike;
import bikeproject.api.model.BikeList;
import bikeproject.api.model.BikeResponse;
import bikeproject.api.model.BikeErrorReponse;
import com.sun.deploy.util.StringUtils;
import okhttp3.Request;
import okio.Buffer;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import retrofit2.Call;
import retrofit2.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BikeService {
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ApiClient.BikeServiceRetrofit bikeServiceRetrofit;
    private Serializer serializer;
    public BikeService(){
        serializer = new Persister();
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
            System.out.println("No hay conexion con el servidor: BikeService.getBikes()");
        }
        return bikeList;
    }
    public BikeResponse saveBike(Bike bike) throws Exception {
        BikeResponse bikeResponse = null;
        Call<BikeResponse> call = bikeServiceRetrofit.saveBike(bike);
        Response<BikeResponse> response = call.execute();
        if (response.isSuccessful()){
            bikeResponse = response.body();;
        }
        else if(response.code()==HttpURLConnection.HTTP_BAD_REQUEST){
            BikeErrorReponse bikeErrorReponse = readXmlInputStream(BikeErrorReponse.class,response.errorBody().byteStream());
            String errorMessages = StringUtils.join(bikeErrorReponse.getErrores(),"\n");
            throw new Exception(errorMessages);
        }
        else if(response.code() == HttpURLConnection.HTTP_CONFLICT){
            bikeResponse = readXmlInputStream(BikeResponse.class,response.errorBody().byteStream());
            throw new Exception(bikeResponse.getMessage());
        }
        else{
            throw new Exception("message: " +response.message() + "Code: " + response.code());
        }
        return bikeResponse;
    }
    public Bike findBikeBySerial(String serial) throws Exception {
        Call<Bike> call = bikeServiceRetrofit.findBikeBySerial(serial);
        Response<Bike> response = call.execute();
        if(response.isSuccessful()){
            return response.body();
        }
        /*
        BikeResponse bikeResponse = readXmlInputStream(BikeResponse.class,response.errorBody().byteStream());
        throw new Exception(bikeResponse.getMessage());*/
        return null;
    }
    public BikeResponse deleteBikeBySerial(String serial) throws Exception {
        Call<BikeResponse> call = bikeServiceRetrofit.deleteBikeBySerial(serial);
        Response<BikeResponse> response = call.execute();
        if(response.isSuccessful()){
            return response.body();
        }
        BikeResponse bikeResponse = readXmlInputStream(BikeResponse.class,response.errorBody().byteStream());
        return bikeResponse;
    }
    public void updateBike(Bike bike) throws Exception {
        Call<Bike> call = bikeServiceRetrofit.updateBike(bike.getSerial(),bike);
        Response<Bike> response = call.execute();
        if(response.isSuccessful()) return;
        if(response.code() == HttpURLConnection.HTTP_BAD_REQUEST){
            BikeErrorReponse bikeErrorReponse = readXmlInputStream(BikeErrorReponse.class,response.errorBody().byteStream());
            String errorMessages = StringUtils.join(bikeErrorReponse.getErrores(),"\n");
            throw new Exception(errorMessages);
        }
        else {
            BikeResponse bikeResponse = readXmlInputStream(BikeResponse.class,response.errorBody().byteStream());
            throw new Exception(bikeResponse.getMessage());
        }
    }
    private <T> T readXmlInputStream(Class<? extends T> someClass, InputStream inputStream ) throws Exception {
        return serializer.read(someClass,inputStream);
    }
    private void testPrintBodyRequest(final Request request) throws IOException {
        final Request copy = request.newBuilder().build();
        final Buffer buffer = new Buffer();
        copy.body().writeTo(buffer);
        System.out.println("Body request");
        System.out.println(buffer.readUtf8());
    }
}
