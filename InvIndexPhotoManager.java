public class InvIndexPhotoManager {
    private BST<LinkedList<Photo>> index;
    private LinkedList<Photo> allPhotos;

    // Constructor
    public InvIndexPhotoManager() {
        index = new BST<LinkedList<Photo>>();
        allPhotos = new LinkedList<Photo>();
    }

    // Add a photo to the inverted index
    public void addPhoto(Photo p) {
        if (photoExists(allPhotos, p)) {
            return; // Photo already exists, no need to add again
        }
        
        // Add photo to the main list
        allPhotos.insert(p);
        
        // Get all tags of the photo
        LinkedList<String> tags = p.getTags();
        if (tags.empty()) {
            return; // No tags to index
        }
        
        // Process each tag
        tags.findFirst();
        while (!tags.last()) {
            String currentTag = tags.retrieve();
            updateIndex(currentTag, p);
            tags.findNext();
        }
        // Process the last tag
        updateIndex(tags.retrieve(), p);
    }

    // Delete a photo from the inverted index
    public void deletePhoto(String path) {
        // First find the photo in the main list
        Photo photoToDelete = findPhotoByPath(path);
        if (photoToDelete == null) {
            return; // Photo not found
        }
        
        // Remove from main list
        removePhotoFromList(allPhotos, photoToDelete);
        
        // Remove from index for each tag
        LinkedList<String> tags = photoToDelete.getTags();
        if (!tags.empty()) {
            tags.findFirst();
            while (!tags.last()) {
                String currentTag = tags.retrieve();
                removePhotoFromTag(currentTag, photoToDelete);
                tags.findNext();
            }
            removePhotoFromTag(tags.retrieve(), photoToDelete);
        }
    }

    // Return the inverted index
    public BST<LinkedList<Photo>> getPhotos() {
        return index;
    }

    // Helper method to check if photo exists in a list
    private boolean photoExists(LinkedList<Photo> list, Photo p) {
        if (list.empty()) {
            return false;
        }
        
        list.findFirst();
        while (!list.last()) {
            if (list.retrieve().getPath().equals(p.getPath())) {
                return true;
            }
            list.findNext();
        }
        return list.retrieve().getPath().equals(p.getPath());
    }

    // Helper method to update index with a new photo for a tag
    private void updateIndex(String tag, Photo p) {
        if (index.findKey(tag)) {
            // Tag exists, add photo to its list
            LinkedList<Photo> photos = index.retrieve();
            if (!photoExists(photos, p)) {
                photos.insert(p);
            }
        } else {
            // Tag doesn't exist, create new entry
            LinkedList<Photo> photos = new LinkedList<Photo>();
            photos.insert(p);
            index.insert(tag, photos);
        }
    }

    // Helper method to remove photo from a tag's list
    private void removePhotoFromTag(String tag, Photo p) {
        if (index.findKey(tag)) {
            LinkedList<Photo> photos = index.retrieve();
            removePhotoFromList(photos, p);
            
            // If tag has no more photos, remove it from index
            if (photos.empty()) {
                index.removeKey(tag);
            }
        }
    }

    // Helper method to remove photo from a list
    private void removePhotoFromList(LinkedList<Photo> list, Photo p) {
        if (list.empty()) {
            return;
        }
        
        list.findFirst();
        while (!list.last()) {
            if (list.retrieve().getPath().equals(p.getPath())) {
                list.remove();
                return;
            }
            list.findNext();
        }
        if (list.retrieve().getPath().equals(p.getPath())) {
            list.remove();
        }
    }

    // Helper method to find photo by path
    private Photo findPhotoByPath(String path) {
        if (allPhotos.empty()) {
            return null;
        }
        
        allPhotos.findFirst();
        while (!allPhotos.last()) {
            if (allPhotos.retrieve().getPath().equals(path)) {
                return allPhotos.retrieve();
            }
            allPhotos.findNext();
        }
        if (allPhotos.retrieve().getPath().equals(path)) {
            return allPhotos.retrieve();
        }
        return null;
    }
}