package controllers;

import models.*;
import utils.FuelTypeUtility;
import utils.Serializer;
import utils.Utilities;

import java.io.*;
import java.util.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class VehicleAPI implements Serializer {
    private List<Vehicle> vehicles = new ArrayList<>();
    private File file;
public VehicleAPI(File file)  {
    this.file = file;
}
    public boolean addVehicle(Vehicle veh) {
        //if the vehicle number doesn't exist in the arraylist, add it
        if (veh != null)
            if (veh.getRegNumber() != null)
                if (getVehicleByRegNumber(veh.getRegNumber()) == null) {
                    return vehicles.add(veh);
        }
        return false;
    }

    public int numberOfVehicles(){
        return vehicles.size();
    }

    public int numberOfScooters(){
        return vehicles.stream().filter(it -> it instanceof Scooter).toList().size();
    }

    public int numberOfElectricCars(){
        return vehicles.stream().filter(it -> it instanceof ElectricCar).toList().size();
    }

    public int numberOfCarbonCars(){
        return vehicles.stream().filter(it -> it instanceof CarbonFuelCar).toList().size();
    }

    //---------------------
    // Read methods
    //---------------------
    public String listAllVehicles(){
        String listAllVehs = "";
        for (Vehicle veh: vehicles){
            listAllVehs += vehicles.indexOf(veh) + ": " + veh + "\n";
        }
        return listAllVehs.equals("") ? "No vehicles" : listAllVehs;
    }

    public String listAllElectricCars(){
        String listAllElectricCars = "";
        for (Vehicle veh: vehicles){
            if (veh instanceof ElectricCar) {
                listAllElectricCars += vehicles.indexOf(veh) + ": " + veh + "\n";
            }
        }
        return listAllElectricCars.equals("") ? "No Electric Cars" : listAllElectricCars;
    }

    public String listAllCarbonFuelCars(){
        String listAllCarbonFuelCars = "";
        for (Vehicle veh: vehicles){
            if (veh instanceof ElectricCar) {
                listAllCarbonFuelCars += vehicles.indexOf(veh) + ": " + veh + "\n";
            }
        }
        return listAllCarbonFuelCars.equals("") ? "No Carbon Fuel Cars" : listAllCarbonFuelCars;
    }
    public String listAllScooters(){
        String listAllScooters = "";
        for (Vehicle veh: vehicles){
            if (veh instanceof Scooter) {
                listAllScooters += vehicles.indexOf(veh) + ": " + veh + "\n";
            }
        }
        return listAllScooters.equals("") ? "No Scooters" : listAllScooters;
    }
    //todo leave this method in starter
    public boolean isValidNewRegNumber(String regNumber){
        for(Vehicle vehicle: vehicles)
            if (vehicle.getRegNumber().equals(regNumber))
                return false;
        return true;
    }
    public String listAllVehiclesAfterAGivenYear(int year){
        String listAllVehiclesAfterAYear = "";
            for (Vehicle veh: vehicles){
                if (veh.getYear() > year) {
                    listAllVehiclesAfterAYear += vehicles.indexOf(veh) + ": " + veh + "\n";
                }
            }

        return listAllVehiclesAfterAYear.equals("") ? "No vehicles bought after " + year : listAllVehiclesAfterAYear;
    }

    public String listAllVehiclesEqualToAGivenYear(int year){
        String listAllVehiclesEqualToAGivenYear = "";
        for (Vehicle veh: vehicles){
            if (veh.getYear() == year) {
                listAllVehiclesEqualToAGivenYear += vehicles.indexOf(veh) + ": " + veh + "\n";
            }
        }

        return listAllVehiclesEqualToAGivenYear.equals("") ? "No vehicles in " + year : listAllVehiclesEqualToAGivenYear;
    }

    public String listAllCarbonFuelsByFuelType(String fuelType){
        if (FuelTypeUtility.validFuelType(fuelType)) {
            String listAllCarbonFuelsByFuelType = "";
            for (Vehicle veh : vehicles) {
                if (veh instanceof CarbonFuelCar) {
                    if (((CarbonFuelCar) veh).getFuelType().equalsIgnoreCase(fuelType)) {
                        listAllCarbonFuelsByFuelType += vehicles.indexOf(veh) + ": " + veh + "\n";
                    }
                }
                return listAllCarbonFuelsByFuelType.equals("") ? "No vehicles in " + fuelType : listAllCarbonFuelsByFuelType;
            }
        }
        return "Fuel Type invalid";
    }

    public String listAllVehicleByChosenManufacturer(Manufacturer manufacturer) {
        String listAllVehicles = "";
        for (Vehicle veh: vehicles){
            if (veh.getManufacturer().equals(manufacturer)){
                listAllVehicles += vehicles.indexOf(veh) + ": " + veh + "\n";
            }
        }
        return listAllVehicles.equals("") ? "No vehicles manufactured by  " + manufacturer : listAllVehicles;
    }

    public int numberOfVehicleByChosenManufacturer(Manufacturer manufacturer) {
        int numVehicles = 0;
        for (Vehicle veh: vehicles){
            if (veh.getManufacturer().equals(manufacturer)){
                numVehicles++ ;
            }
        }
        return numVehicles;
    }
    public Vehicle getVehicleByRegNumber(String regNumber){
        for (Vehicle veh: vehicles){
                if (veh.getRegNumber().equalsIgnoreCase(regNumber)) {
                    return veh;
                }
            }
        return null;
    }

    public Vehicle getVehicleByIndex(int index){
       if (Utilities.isValidIndex(vehicles, index)){
           return vehicles.get(index);
        }
        return null;
    }

    public Vehicle deleteVehicleByRegNumber(String regNumber){
        Vehicle veh = getVehicleByRegNumber(regNumber);
        if ((veh != null)){
            vehicles.remove(veh);
            return veh;
        }
        return null;
    }

    public Vehicle deleteVehicleByIndex(int index){
        if (Utilities.isValidIndex(vehicles,index)){
            return vehicles.remove(index);
        }
        return null;
    }

    //----------------------------------------------
    //Reduced sorting code just to get tests written
    //----------------------------------------------
    public void sortByCostDescending() {
        vehicles.sort(Comparator.comparingDouble(Vehicle::getCost).reversed());
    }

    public void sortByAgeAscending() {
        vehicles.sort(Comparator.comparingDouble(Vehicle::getAge));
    }

    public void sortByCarbonFootprintAscending() {
        vehicles.sort(Comparator.comparingDouble(Vehicle::getCarbonFootPrint));
    }

    public void sortByCarbonFootprintDescending() {
        vehicles.sort(Comparator.comparingDouble(Vehicle::getCarbonFootPrint).reversed());
    }

    public List<Vehicle> topFiveCarbonVehicles (){
        sortByCarbonFootprintAscending();  //sort by lowest to highest footprint.
        return vehicles.stream()
                .filter(it -> it instanceof CarbonFuelCar)
                .limit(5)
                .toList();
    }

    public boolean upDateCarbonFuelCar(String regNumber, CarbonFuelCar updatedDetails)  {
        Vehicle foundVehicle = getVehicleByRegNumber(regNumber);

        if ((foundVehicle instanceof CarbonFuelCar)){
            //Generic Fields in Vehicle
            foundVehicle.setCost(updatedDetails.getCost());
            foundVehicle.setModel(updatedDetails.getModel());
            foundVehicle.setManufacturer(updatedDetails.getManufacturer());
            foundVehicle.setYear(updatedDetails.getYear());
            //Generic Fields in Car
            ((CarbonFuelCar) foundVehicle).setPower(updatedDetails.getPower());
            ((CarbonFuelCar) foundVehicle).setSecs0To60(updatedDetails.getSecs0To60());
            ((CarbonFuelCar) foundVehicle).setTopSpeed(updatedDetails.getTopSpeed());
            ((CarbonFuelCar) foundVehicle).setTorque(updatedDetails.getTorque());
            //Specific Fields
            ((CarbonFuelCar) foundVehicle).setCarbonEmission(updatedDetails.getCarbonEmission());
            ((CarbonFuelCar) foundVehicle).setFuelConsumption(updatedDetails.getFuelConsumption());
            ((CarbonFuelCar) foundVehicle).setFuelType(updatedDetails.getFuelType());
            ((CarbonFuelCar) foundVehicle).setAutomatic(updatedDetails.isAutomatic());
            ((CarbonFuelCar) foundVehicle).setEngineSize(updatedDetails.getEngineSize());
            return true;
        }
        return false;
    }

    public boolean upDateElectricCar(String regNumber, ElectricCar updatedDetails)  {
        Vehicle foundVehicle = getVehicleByRegNumber(regNumber);

        if ((foundVehicle instanceof ElectricCar)){
            //Generic Fields in Vehicle
            foundVehicle.setCost(updatedDetails.getCost());
            foundVehicle.setModel(updatedDetails.getModel());
            foundVehicle.setManufacturer(updatedDetails.getManufacturer());
            foundVehicle.setYear(updatedDetails.getYear());
            //Generic Fields in Car
            ((ElectricCar) foundVehicle).setPower(updatedDetails.getPower());
            ((ElectricCar) foundVehicle).setSecs0To60(updatedDetails.getSecs0To60());
            ((ElectricCar) foundVehicle).setTopSpeed(updatedDetails.getTopSpeed());
            ((ElectricCar) foundVehicle).setTorque(updatedDetails.getTorque());
            //Specific Fields
            ((ElectricCar) foundVehicle).setEngineKWatts(updatedDetails.getEngineKWatts());
            ((ElectricCar) foundVehicle).setRange(updatedDetails.getRange());
            return true;
        }
        return false;
    }

    public boolean upDateScooter(String regNumber, Scooter updatedDetails)  {
        Vehicle foundVehicle = getVehicleByRegNumber(regNumber);

        if ((foundVehicle instanceof Scooter)){
            //Generic Fields in Vehicle
            foundVehicle.setCost(updatedDetails.getCost());
            foundVehicle.setModel(updatedDetails.getModel());
            foundVehicle.setManufacturer(updatedDetails.getManufacturer());
            foundVehicle.setYear(updatedDetails.getYear());
            //Specific Fields
            ((Scooter) foundVehicle).setPower(updatedDetails.getPower());
            ((Scooter) foundVehicle).setWeight(updatedDetails.getWeight());
            ((Scooter) foundVehicle).setTopRiderWeight(updatedDetails.getTopRiderWeight());
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
        Class<?>[] classes = new Class[]{Vehicle.class, Car.class, CarbonFuelCar.class,
                                            ElectricCar.class, Scooter.class, Manufacturer.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        vehicles = (List<Vehicle>) in.readObject();
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
        out.writeObject(vehicles);
        out.close();
    }

    public String fileName(){
        return this.file.toString();
    }


}
