package controllers;

import models.*;
import utils.ISerializer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CelestialSystemAPI implements ISerializer {

    private List<CelestialBody> celestialList = new ArrayList<>();
    private File file;

    public CelestialSystemAPI(File file)  {
        this.file = file;
    }

    public String listAllCelestialBodies() {
        if (celestialList.isEmpty()) {
            return "There are no celestial bodies in the system at the moment";
        } else {
            String celestialListString = "List of Celestial Bodies:\n";
            for (CelestialBody celestial : celestialList) {
                celestialListString += celestial.toString() + "\n";
            }
            return celestialListString;
        }
    }
    public String listAllStarsForSpectralType(char type) {

        if (celestialList.isEmpty()) {
            return "There are no celestial bodies in the system at the moment";
        } else {
            String celestialListString = "";
            for (CelestialBody celestial : celestialList) {
                celestialListString += celestial.toString() + "\n";
            }
            if (!celestialListString.isEmpty())
                return"List of Celestial Bodies:\n"+  celestialListString;
            else return "There are no celestial bodies in the system of type " + type;
        }
    }

    public boolean isValidIndex(int index) {
        return (index >=0 &&index< celestialList.size());
    }
    public int isValidId(int id) {  //return position if exists otherwise -1
        for (int i = 0; i < celestialList.size(); i++) {
            if (id == celestialList.get(i).getId()) return i;
        }
        return (-1);
    }

    public CelestialBody deleteCelestialIndex(int index) {
       if (isValidIndex(index)) {
           return celestialList.remove(index);
       }
       else return null;
    }
    public CelestialBody deleteCelestialId(int id)  {
        int tryFind = isValidId(id);
        if (tryFind!= -1) {
           return celestialList.remove(tryFind);
        }
        return null;
    }
    public CelestialBody getCelestialBodyByIndex(int index) {
        if (isValidIndex(index)) {
            return celestialList.get(index);
        }
        return null;
    }
    public CelestialBody getCelestialBodyById(int id) {
        int tryFind = isValidId(id);
        if (tryFind != -1) {
            return celestialList.get(tryFind);
        }
        return null;
    }
    public boolean addCelestialObject(CelestialBody celestial) {
        return celestialList.add(celestial);
    }

    public String listAllGasPlanets() {
       if (celestialList.isEmpty()) {
           return "There are no celestial bodies in the system at the moment";
       }
       else {
           String gasPlanetList = "";
           for (CelestialBody celestial : celestialList) {
               if (celestial instanceof GasPlanet) {
                   gasPlanetList += celestial.toString() + "\n";
               }
           }
           if (gasPlanetList.isEmpty()){
               return "There are no Gas Planets in the system at the moment ";
           }
           else return gasPlanetList;
       }
    }

    public String listAllIcePlanets() {
        if (celestialList.isEmpty()) {
            return "There are no celestial bodies in the system at the moment";
        }
        else {
            String icePlanetList = "";
            for (CelestialBody celestial : celestialList) {
                if (celestial instanceof IcePlanet) {
                    icePlanetList += celestial.toString() + "\n";
                }
            }
            if (icePlanetList.isEmpty()){
                return "There are no Ice Planets in the system at the moment ";
            }
            else return icePlanetList;
        }
    }

    public String listAllStars() {
        if (celestialList.isEmpty()) {
            return "There are no celestial bodies in the system at the moment";
        }
        else {
            String starList = "";
            for (CelestialBody celestial : celestialList) {
                if (celestial instanceof Star) {
                    starList += celestial.toString() + "\n";
                }
            }
            if (starList.isEmpty()){
                return "There are no Stars in the system at the moment ";
            }
            else return starList;
        }
    }





    public String listAllCelestialObjectsHeavierThan(double mass) {
        if (celestialList.isEmpty()) {
            return "There are no celestial bodies in the system at the moment";
        }
        else {
            String heavyOnes = "";
            for (CelestialBody celestial : celestialList) {
                if (celestial.getMass() > mass) {
                    heavyOnes += celestial.toString() + "\n";
                }
            }
            if (heavyOnes.isEmpty()){
                return "There are no celestial bodies in the system at the moment heavier than "+ mass;
            }
            else return heavyOnes;
        }
    }

    public String listAllCelestialObjectsSmallerThan(double diam) {
        if (celestialList.isEmpty()) {
            return "There are no celestial bodies in the system at the moment";
        }
        else {
            String smallOnes = "";
            for (CelestialBody celestial : celestialList) {
                if (celestial.getDiameter() > diam) {
                    smallOnes += celestial.toString() + "\n";
                }
            }
            if (smallOnes.isEmpty()){
                return "There are no celestial bodies in the system at the moment with a diameter smaller than "+ diam;
            }
            else return smallOnes;
        }
    }
    public boolean bodyHasAsPlanetarySystem(CelestialBody cb, PlanetarySystem m){
        return (cb.getPlanetarySystem().equals(m));
    }
    public String listAllCelestialObjectsForGivenPlanetary(PlanetarySystem m) {
        if (celestialList.isEmpty()) {
            return "There are no celestial bodies in the system at the moment";
        }
        else {
            String allForP = "";
            for (CelestialBody celestial : celestialList) {
                if (bodyHasAsPlanetarySystem(celestial, m)) {
                    allForP += celestial.toString() + "\n";
                }
            }
            if (allForP.isEmpty()){
                return "There are no celestial bodies in the system at the moment whose planetary system is  "+ m;
            }
            else return allForP;
        }
    }

    public int numberOfCelestialBodies(){ return celestialList.size(); }
    public int numberOfStars() {
        int numberOfStars = 0;
        for (CelestialBody celestial : celestialList) {
            if (celestial instanceof Star) {
                numberOfStars++;
            }
        }
        return numberOfStars;
    }
    public int numberOfIcePlanets(){
        int numberOfIcePlanets = 0;
        for (CelestialBody celestial : celestialList) {
            if (celestial instanceof IcePlanet) {
                numberOfIcePlanets++;
            }
        }
        return numberOfIcePlanets;}
    public int numberOfGasPlanets() {
        int numberOfGasPlanets = 0;
        for (CelestialBody celestial : celestialList) {
            if (celestial instanceof IcePlanet) {
                numberOfGasPlanets++;
            }
        }
        return numberOfGasPlanets;
    }
    public int numberOfCelestialBodyByChosenPlanetarySystem(PlanetarySystem m ){
        int numberOfcelByPl = 0;
        for (CelestialBody celestial : celestialList) {
            if (bodyHasAsPlanetarySystem(celestial, m)) {
                numberOfcelByPl++;
            }
        }
        return numberOfcelByPl;}


    public CelestialBody updateStar(int id, Star updatedDetails){
        int toUpdate = isValidId(id);
        if (toUpdate != -1){
        return celestialList.set(toUpdate, (CelestialBody) updatedDetails);
        }
        return null;
    }

    public CelestialBody updateGasPlanet(int id, GasPlanet updatedDetails){
        int toUpdate = isValidId(id);
        if (toUpdate != -1){
            return celestialList.set(toUpdate, updatedDetails);
        }
        return null;
    }
    public CelestialBody updateIcePlanet(int id, IcePlanet updatedDetails){
        int toUpdate = isValidId(id);
        if (toUpdate != -1){
            return celestialList.set(toUpdate, updatedDetails);
        }
        return null;
    }

    public void sortByDiameterAscending(){

       for (int i = 0; i < celestialList.size(); i++) {
           int greatestSoFar = i;
           for (int j = i + 1; j < celestialList.size(); j++) {
               if (celestialList.get(j).getDiameter() > celestialList.get(greatestSoFar).getDiameter()) {
                   greatestSoFar = j;
               }
               swapCelestialBody(celestialList, i, j);

           }
       }
    }

    private void swapCelestialBody (List<CelestialBody> celestialList, int i, int j){
        CelestialBody celestial = celestialList.get(i);
        celestialList.set(i, celestialList.get(j));
        celestialList.set(j, celestial);
    }
    public String topFiveHighestRadiationGasPlanet() {
        return "";
    }

    @Override
    public void save() throws Exception {

    }

    @Override
    public void load() throws Exception {

    }

    @Override
    public String fileName() {
        return file.getName();
    }
}   
