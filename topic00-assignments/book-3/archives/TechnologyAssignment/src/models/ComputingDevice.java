package models;

public abstract class ComputingDevice extends Technology {

    private String processor = "";  //20 chars or less
    private int storage = 8; //must be between 8 and 128, and must be divisible by 8

    // Constructor


    public ComputingDevice(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage) {
        super(modelName, price, manufacturer, id);
        this.processor = utils.Utilities.truncateString(processor, 20);
        setStorage(storage);
    }

    // Getters and setters
    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        if(utils.Utilities.validStringlength(processor, 20))
            this.processor = processor;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        if(storage >=8 && storage <= 128 && storage%8==0)
            this.storage = storage;
    }


    @Override
    public String toString() {
        return
                super.toString()+
                        "\nProcessor: " + processor +
                        ", Storage: " + storage + "GB" ;

    }
    }

