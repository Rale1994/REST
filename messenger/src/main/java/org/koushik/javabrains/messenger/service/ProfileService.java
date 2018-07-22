package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = DatabaseClass.getProfile();

	public ProfileService() {
		profiles.put("koushik", new Profile(1L, "koushik", "Koushik", "Kathogal"));
	}

	public ArrayList<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);

	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		//prima kao ulazni parametar objekat klase Profile 
		// prvo se pitam da li ime profile u dozvoljenoj vrednosti
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		// u suprotnom uzmi hashmapu profiles i u nju ubaci objekat profile nad kojim se
		// izvrsava metoda getPrileName i na kraju vrati taj objekat
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
