package bikeproject.api.model;

import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "HashMap")
public class BikerErrorReponse {

    List<String> message;
}
