package controllers;

import models.*;

import utils.ISerializer;
import utils.OperatingSystemUtility;

import utils.Utilities;

import java.io.*;
import java.util.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class TechnologyDeviceAPI implements ISerializer {
    private List<Technology> technologyList = new ArrayList<>();
    private File file;

    public TechnologyDeviceAPI(File file)  {
    this.file = file;
}
    public boolean addTechnologyDevice(Technology techDevice) {
        //if the device id doesn't exist in the arraylist, add it
        if (techDevice != null)
            if (techDevice.getId() != null)
                if (getTechnologyDeviceById(techDevice.getId()) == null) {
                    return technologyList.add(techDevice);
        }
        return false;
    }



    public int numberOfTechnologyDevices(){
        return technologyList.size();
    }

    public int numberOfTablets(){
        return technologyList.stream().filter(it -> it instanceof Tablet).toList().size();
    }
    public int numberOfSmartBands(){
        return technologyList.stream().filter(it -> it instanceof SmartBand).toList().size();
    }
    public int numberOfSmartWatch(){
        return technologyList.stream().filter(it -> it instanceof SmartWatch).toList().size();
    }
    //---------------------
    // Read methods
    //---------------------
    public String listAllTechnologyDevices(){
        String listAllTech = "";
        for (Technology techDev: technologyList){
            listAllTech += technologyList.indexOf(techDev) + ": " + techDev + "\n";
        }
        return listAllTech.equals("") ? "No Technology Devices" : listAllTech;
    }

    public String listAllSmartBands(){
        String listAllSmartBandsStr = "";
        for (Technology techDev: technologyList){
            if (techDev instanceof SmartBand) {
                listAllSmartBandsStr += technologyList.indexOf(techDev) + ": " + techDev + "\n";
            }
        }
        return listAllSmartBandsStr.equals("") ? "No Smart Bands" : listAllSmartBandsStr;
    }

    public String listAllSmartWatches(){
        String listAllSmartWatchStr = "";
        for (Technology techDev: technologyList){
            if (techDev instanceof SmartWatch) {
                listAllSmartWatchStr += technologyList.indexOf(techDev) + ": " + techDev + "\n";
            }
        }
        return listAllSmartWatchStr.equals("") ? "No Smart Watches" : listAllSmartWatchStr;
    }
    public String listAllTablets(){
        String listAllTabletsStr = "";
        for (Technology techDev: technologyList){
            if (techDev instanceof Tablet) {
                listAllTabletsStr += technologyList.indexOf(techDev) + ": " + techDev + "\n";
            }
        }
        return listAllTabletsStr.equals("") ? "No Tablets" : listAllTabletsStr;
    }
    //todo leave this method in starter
    public boolean isValidId(String id) {
        for (Technology techDev : technologyList) {
            if (techDev.getId().equals(id))
                return false;
        }
            return true;
        }

    public String listAllTechnologyAbovePrice(double price){
        String listAllTechnologyAbovePriceStr = "";
            for (Technology techDev: technologyList){
                if (techDev.getPrice() >= price) {
                    listAllTechnologyAbovePriceStr += technologyList.indexOf(techDev) + ": " + techDev + "\n";
                }
            }

        return listAllTechnologyAbovePriceStr.equals("") ? "No technology more expensive than "
                + price : listAllTechnologyAbovePriceStr;
    }

    public String listAllTechnologyBelowPrice(double price){
        String listAllTechnologyBelowPriceStr = "";
        for (Technology techDev: technologyList){
            if (techDev.getPrice() <= price) {
                listAllTechnologyBelowPriceStr += technologyList.indexOf(techDev) + ": " + techDev + "\n";
            }
        }

        return listAllTechnologyBelowPriceStr.equals("") ? "No technology cheaper than "
                + price : listAllTechnologyBelowPriceStr;
    }

    public String listAllTabletsByOperatingSystem(String os){
        if (OperatingSystemUtility.isValidOperatingSystem(os)) {
            String listAllTabletsByOperatingSystemStr = "";
            for (Technology techDev: technologyList){
                if (techDev instanceof Tablet) {
                    if (((Tablet) techDev).getOperatingSystem().equalsIgnoreCase(os)) {
                        listAllTabletsByOperatingSystemStr +=
                                technologyList.indexOf(techDev) + ": " + techDev + "\n";
                    }
                }
                return listAllTabletsByOperatingSystemStr.equals("") ? "No tablet with the operating system "
                        + os : listAllTabletsByOperatingSystemStr;
            }
        }
        return "Invalid Operating System";
    }

    public String listAllTechDevicesByChosenManufacturer(Manufacturer manufacturer) {
        String listAllTechDevicesStr = "";
        for (Technology techDev: technologyList){
            if (techDev.getManufacturer().equals(manufacturer)){
                listAllTechDevicesStr += technologyList.indexOf(techDev) + ": " + techDev + "\n";
            }
        }
        return listAllTechDevicesStr.equals("") ?
                "No technology manufactured by  " + manufacturer : listAllTechDevicesStr;
    }

    public int numberOfTechnologyByChosenManufacturer(Manufacturer manufacturer) {
        int numTechDevices = 0;
        for (Technology techDev: technologyList){
            if (techDev.getManufacturer().equals(manufacturer)){
                numTechDevices++;
                 }
        }
        return numTechDevices;
    }

    private Technology getTechnologyDeviceById(String id) {
        for(Technology compDev:technologyList){
            if(compDev.getId().equalsIgnoreCase(id))
                return compDev;
        }

        return null;
    }
    public Technology getTechnologyByIndex(int index){
       if (Utilities.isValidIndex(technologyList, index)){
           return technologyList.get(index);
        }
        return null;
    }

    public Technology deleteTechnologyById(String id){
        Technology tech = getTechnologyDeviceById(id);
        if ((tech != null)){
            technologyList.remove(tech);
            return tech;
        }
        return null;
    }

    public Technology deleteTechnologyByIndex(int index){
        if (Utilities.isValidIndex(technologyList,index)){
            return technologyList.remove(index);
        }
        return null;
    }

    //----------------------------------------------
    //Reduced sorting code just to get tests written
    //----------------------------------------------
    public void sortByPriceDescending() {
        technologyList.sort(Comparator.comparingDouble(Technology::getPrice).reversed());
    }

    public void sortByPriceAscending() {
        technologyList.sort(Comparator.comparingDouble(Technology::getPrice));
    }

public void swapTechnology (List<Technology> technologyList, int i, int j){

}

    public List<Technology> topFiveMostExpensiveTechnology (){
        sortByPriceAscending();  //sort by lowest to highest price
        return technologyList.stream()
                .limit(5)
                .toList();
    }
    public List<Technology> topFiveMostExpensiveTablet (){
        sortByPriceAscending();  //sort by lowest to highest .
        return technologyList.stream()
                .filter(it -> it instanceof Tablet)
                .limit(5)
                .toList();
    }
    public List<Technology> topFiveMostExpensiveSmartWatch (){
        sortByPriceAscending();  //sort by lowest to highest .
        return technologyList.stream()
                .filter(it -> it instanceof SmartWatch)
                .limit(5)
                .toList();
    }

    public boolean updateTablet(String id, Tablet updatedDetails)  {
        Technology foundDevice= getTechnologyDeviceById(id);

        if ((foundDevice instanceof Tablet)){
            //Generic Fields in Tech
            foundDevice.setPrice(updatedDetails.getPrice());
            foundDevice.setModelName(updatedDetails.getModelName());
            foundDevice.setManufacturer(updatedDetails.getManufacturer());

            //Generic Fields in ComputingDevice
            ((Tablet) foundDevice).setProcessor(updatedDetails.getProcessor());
            ((Tablet) foundDevice).setStorage(updatedDetails.getStorage());
            //Specific Fields
            ((Tablet) foundDevice).setOperatingSystem(updatedDetails.getOperatingSystem());
            return true;
        }
        return false;
    }

    public boolean updateSmartWatch(String id, SmartWatch updatedDetails)  {
        Technology foundDevice= getTechnologyDeviceById(id);

        if ((foundDevice instanceof SmartWatch)){
            //Generic Fields in Tech
            foundDevice.setPrice(updatedDetails.getPrice());
            foundDevice.setModelName(updatedDetails.getModelName());
            foundDevice.setManufacturer(updatedDetails.getManufacturer());

            //Generic Fields in ComputingDevice
            ((SmartWatch) foundDevice).setSize(updatedDetails.getSize());
            ((SmartWatch) foundDevice).setMaterial(updatedDetails.getMaterial());
            //Specific Fields
            ((SmartWatch) foundDevice).setDisplayType(updatedDetails.getDisplayType());
            return true;
        }
        return false;
    }

    public boolean updateSmartBand(String id, SmartBand updatedDetails)  {
        Technology foundDevice= getTechnologyDeviceById(id);

        if ((foundDevice instanceof SmartBand)){
            //Generic Fields in Tech
            foundDevice.setPrice(updatedDetails.getPrice());
            foundDevice.setModelName(updatedDetails.getModelName());
            foundDevice.setManufacturer(updatedDetails.getManufacturer());

            //Generic Fields in ComputingDevice
            ((SmartBand) foundDevice).setSize(updatedDetails.getSize());
            ((SmartBand) foundDevice).setMaterial(updatedDetails.getMaterial());
            //Specific Fields
            ((SmartBand) foundDevice).setHeartRateMonitor(updatedDetails.isHeartRateMonitor());
            return true;
        }
        return false;
    }

    //---------------------
    // Persistence methods
    //---------------------
    /**
     * The load method uses the XStream component to read all the objects from the xml
     * file stored on the hard disk.  The read objects are loaded into the associated ArrayList
     *
     * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{Technology.class, ComputingDevice.class, WearableDevice.class,
                                            SmartWatch.class, SmartBand.class, Tablet.class, Manufacturer.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        technologyList = (List<Technology>) in.readObject();
        in.close();
    }

    /**
     * The save method uses the XStream component to write all the objects in the ArrayList
     * to the xml file stored on the hard disk.
     *
     * @throws Exception An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
        out.writeObject(technologyList);
        out.close();
    }

    public String fileName(){
        return this.file.toString();
    }


}
