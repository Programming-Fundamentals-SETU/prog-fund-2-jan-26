package persistence;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import java.io.*;

//http://x-stream.github.io/json-tutorial.html
//note: needs both xstream and jettison

public class JSONSerializer implements Serializer {

    private File file;

    public JSONSerializer(File file) {
        this.file = file;
    }

    @SuppressWarnings("unchecked")
    public Object read() throws Exception {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        ObjectInputStream is = xStream.createObjectInputStream(new FileReader(file));
        Object object = (Object) is.readObject();
        is.close();
        return object;
    }

    public void write(Object object) throws Exception {
        var xstream = new XStream(new JettisonMappedXmlDriver());
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(object);
        os.close();
    }

}