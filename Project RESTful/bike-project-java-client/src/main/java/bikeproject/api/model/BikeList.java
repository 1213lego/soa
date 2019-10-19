package bikeproject.api.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "List")
public class BikeList {
    @ElementList(inline=true,required = false)
    private List<Bike> bikeList;
    public BikeList(){
        bikeList = new ArrayList<>();
    }
    public List<Bike> getBikeList() {
        return bikeList;
    }

    public void setBikeList(List<Bike> bikeList) {
        this.bikeList = bikeList;
    }
}
