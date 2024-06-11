import java.util.*;

public class CandidateLocationMatching {

    public static void main(String[] args) {
        List<String> candidates = Arrays.asList("A", "B", "C");
        List<String> locations = Arrays.asList("X", "Y", "Z");

        Map<String, List<String>> candidatePrefs = new HashMap<>();
        candidatePrefs.put("A", Arrays.asList("X", "Y", "Z"));
        candidatePrefs.put("B", Arrays.asList("Y", "Z", "X"));
        candidatePrefs.put("C", Arrays.asList("Z", "X", "Y"));

        Map<String, List<String>> locationPrefs = new HashMap<>();
        locationPrefs.put("X", Arrays.asList("B", "A", "C"));
        locationPrefs.put("Y", Arrays.asList("A", "C", "B"));
        locationPrefs.put("Z", Arrays.asList("C", "B", "A"));

        Map<String, String> candidateMatch = matchCandidatesToLocations(candidates, locations, candidatePrefs, locationPrefs);

        System.out.println("Candidate Matches: " + candidateMatch);
    }

    public static Map<String, String> matchCandidatesToLocations(
            List<String> candidates,
            List<String> locations,
            Map<String, List<String>> candidatePrefs,
            Map<String, List<String>> locationPrefs) {

        Map<String, String> candidateMatch = new HashMap<>();
        Map<String, String> locationMatch = new HashMap<>();
        Queue<String> unmatchedCandidates = new LinkedList<>(candidates);

        while (!unmatchedCandidates.isEmpty()) {
            String candidate = unmatchedCandidates.poll();
            List<String> candidatePrefList = candidatePrefs.get(candidate);

            for (String location : candidatePrefList) {
                if (!candidateMatch.containsKey(candidate)) {
                    if (!locationMatch.containsKey(location)) {
                        // Location is free, match candidate and location
                        candidateMatch.put(candidate, location);
                        locationMatch.put(location, candidate);
                    } else {
                        // Location is already matched, check preference
                        String currentMatch = locationMatch.get(location);
                        List<String> locationPrefList = locationPrefs.get(location);

                        if (locationPrefList.indexOf(candidate) < locationPrefList.indexOf(currentMatch)) {
                            // Location prefers the new candidate over the current match
                            candidateMatch.remove(currentMatch);
                            unmatchedCandidates.add(currentMatch);

                            candidateMatch.put(candidate, location);
                            locationMatch.put(location, candidate);
                        }
                    }
                }
            }
        }

        return candidateMatch;
    }
}