package persistence;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;

public class XMLSerializer implements Serializer {

    private File file;

    public XMLSerializer(File file) {
        this.file = file;
    }

    @SuppressWarnings("unchecked")
    public Object read() throws Exception {

        XStream xStream = new XStream(new DomDriver());
        ObjectInputStream is = xStream.createObjectInputStream(new FileReader(file));
        Object object = (Object) is.readObject();
        is.close();
        return object;

    }

    public void write(Object object) throws Exception {
        var xstream = new XStream(new DomDriver());
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(object);
        os.close();
    }

}