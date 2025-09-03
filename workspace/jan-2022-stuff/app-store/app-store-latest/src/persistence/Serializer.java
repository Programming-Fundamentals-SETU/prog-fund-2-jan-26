package persistence;

public interface Serializer {

   void write(Object obj) throws Exception;
   Object read() throws Exception;

}