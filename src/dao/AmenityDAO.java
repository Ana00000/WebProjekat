package dao;

import java.util.HashMap;
import beans.Amenity;
import beans.User;

public class AmenityDAO {
	private HashMap<Integer, Amenity> amenities = new HashMap<Integer, Amenity>();

	public AmenityDAO() {
		amenities.put(1, new Amenity(1, "Crib"));
		amenities.put(2, new Amenity(2, "High chair"));
		amenities.put(3, new Amenity(3, "Elevator"));
	}

	@Override
	public String toString() {
		return "AmenityDAO [amenities=" + amenities + "]";
	}

	public HashMap<Integer, Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(HashMap<Integer, Amenity> amenities) {
		this.amenities = amenities;
	}

	public Amenity find(Amenity a) {
		for(Amenity amenity: amenities.values())
		{
		if(a.getName().equals(amenity.getName()))
			if(a.getId() == (amenity.getId()))
			{
				return amenity;
			}
		}
		
		return null;
	}
}
