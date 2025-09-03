package persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

//todo consider removing this one as the XStream XMLSerializer XML file format is incompatible with this one.

public class XMLEncoderSerializer implements Serializer {

    private File file;

    public XMLEncoderSerializer(File file) {
        this.file = file;
    }

    public void write(Object obj) throws Exception{
        XMLEncoder encoder =
                new XMLEncoder(
                        new BufferedOutputStream(
                                new FileOutputStream(file)));
        encoder.writeObject(obj);
        encoder.close();
    }

    public Object read() throws Exception {
        XMLDecoder decoder =
                new XMLDecoder(new BufferedInputStream(
                        new FileInputStream(file)));
        Object obj = (Object)decoder.readObject();
        decoder.close();
        return obj;
    }
}