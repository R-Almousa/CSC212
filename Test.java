
public class Test {
    public static void main(String[] args) {
        PhotoManager manager = new PhotoManager();
        
        Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        manager.addPhoto(photo1);
        
        Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        manager.addPhoto(photo2);
        
        Photo photo3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
        manager.addPhoto(photo3);
        
        Album album1 = new Album("Album1", "bear", manager);
        Album album2 = new Album("Album2", "animal AND grass", manager);
        
        System.out.println("Get photo1 path and tags:");
        System.out.println("photo1 path: " + photo1.getPath());
        System.out.println("photo1 tags: " + getTagsAsString(photo1.getTags()));
        
        System.out.println("\nGet album2 name, condition, and photos:");
        System.out.println("album2 name: " + album2.getName());
        System.out.println("album2 condition: " + album2.getCondition());
        System.out.println("album2 photos: " + getPhotosAsString(album2.getPhotos()));
        
        System.out.println("\nDelete the photo 'bear.jpg':");
        manager.deletePhoto("bear.jpg");
        
        // Verify deletion
        System.out.println("\nAfter deletion, album2 photos: " + getPhotosAsString(album2.getPhotos()));
    }

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();
        String[] tagsArray = tags.split("\\s*,\\s*");
        for (int i = 0; i < tagsArray.length; i++) {
            result.insert(tagsArray[i]);
        }
        return result;
    }

    // Helper method to print tags
    private static String getTagsAsString(LinkedList<String> tags) {
        if (tags.empty()) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        tags.findFirst();
        while (!tags.last()) {
            sb.append(tags.retrieve()).append(", ");
            tags.findNext();
        }
        sb.append(tags.retrieve()).append("]");
        return sb.toString();
    }

    // Helper method to print photo paths
    private static String getPhotosAsString(LinkedList<Photo> photos) {
        if (photos.empty()) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        photos.findFirst();
        while (!photos.last()) {
            sb.append(photos.retrieve().getPath()).append(", ");
            photos.findNext();
        }
        sb.append(photos.retrieve().getPath()).append("]");
        return sb.toString();
    }
}
