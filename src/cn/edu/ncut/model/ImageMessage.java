package cn.edu.ncut.model;

/**
 * @author NikoBelic
 * @create 15:14
 */
public class ImageMessage extends BaseMessage
{
    private Image Image;

    public cn.edu.ncut.model.Image getImage() {
        return Image;
    }

    public void setImage(cn.edu.ncut.model.Image image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "ImageMessage{" +
                "Image=" + Image +
                '}';
    }
}
