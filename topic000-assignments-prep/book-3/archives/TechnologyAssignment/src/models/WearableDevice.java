package models;

import utils.Utilities;

// WearableDevices class extending Technology
public abstract class WearableDevice extends Technology {
    private String size; //10 chars or less
    private String material;  //20 chars or less

    // Constructor


    public WearableDevice(String modelName, double price, Manufacturer manufacturer, String id, String size, String material) {
        super(modelName, price, manufacturer, id);
        this.size = Utilities.truncateString(size, 10);
        this.material = Utilities.truncateString(material, 20);
    }

    // Getters and setters
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if(Utilities.validStringlength(size, 10))
            this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if(Utilities.validStringlength(material, 20))
        this.material = material;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nSize: " + size+
                ",Material: " + material;
    }
}
