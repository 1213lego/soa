package bikeproject.api.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "HashMap")
public class BikerErrorReponse {
    @ElementList(entry = "errores",inline = true)
    List<String> errores;

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }
}
