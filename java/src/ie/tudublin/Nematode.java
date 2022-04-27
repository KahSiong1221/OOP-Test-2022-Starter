package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Nematode 
{
    private String name;
    private int length;
    private boolean limbs;
    private char gender;
    private boolean eyes;

    
    public Nematode(String name, int length, boolean limbs, char gender, boolean eyes) {
        this.name = name;
        this.length = length;
        this.limbs = limbs;
        this.gender = gender;
        this.eyes = eyes;
    }

    public Nematode(TableRow tr)
    {
        this(
            tr.getString("name"),
            tr.getInt("length"),
            tr.getInt("limbs") == 1,
            tr.getString("gender").charAt(0),
            tr.getInt("eyes") == 1
        );
    }

    @Override
    public String toString() {
        return "Nematode [eyes=" + eyes + ", gender=" + gender + ", length=" + length + ", limbs=" + limbs + ", name="
                + name + "]";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public boolean isLimbs() {
        return limbs;
    }
    public void setLimbs(boolean limbs) {
        this.limbs = limbs;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public boolean isEyes() {
        return eyes;
    }
    public void setEyes(boolean eyes) {
        this.eyes = eyes;
    }

    public void render(NematodeVisualiser pa)
    {
        float segmentSize = 30;
        float radius = segmentSize / 2;

        float border = (pa.height - (length * segmentSize)) / 2 + radius;
        float y;
        float x = pa.width / 2;

        pa.ellipseMode(PApplet.CENTER);
        pa.noFill();
        pa.stroke(255);

        // name
        pa.textSize(16);
        pa.textAlign(PApplet.CENTER, PApplet.CENTER);
        pa.text(name, x, border - 2 * segmentSize);

        // eyes
        if (eyes == true)
        {
            float angle = PApplet.TWO_PI / 8.0f;
            float x2 = radius * PApplet.sin(3 * angle);
            float y2 = radius * PApplet.cos(3 * angle);
            pa.line(x + x2, border + y2, x + radius + x2, border - radius + y2);
            pa.circle(x + radius + x2, border - radius + y2, 7);
            x2 = radius * PApplet.sin(angle);
            y2 = radius * PApplet.cos(angle);
            pa.line(x - x2, border - y2, x - radius - x2, border - radius - y2);
            pa.circle(x - radius - x2, border - radius - y2, 7);
        }

        for (int i = 1; i <= length; i++)
        {
            if (length == 1)
            {
                y = pa.height / 2;
            }
            else
            {
                y = PApplet.map(i, 1, length, border, pa.height - border);
            }

            // segment
            pa.circle(x, y, segmentSize);
            // limbs
            if (limbs == true)
            {
                pa.line(x - radius, y, x - radius - 15, y);
                pa.line(x + radius, y, x + radius + 15, y);
            }
        }

        if (length == 1)
        {
            y = pa.height / 2;
        }
        else
        {
            y = PApplet.map(length, 1, length, border, pa.height - border);
        }

        switch(gender)
        {
            case 'm':
            {
                pa.line(x, y + radius, x, y + 2 * radius);
                pa.circle(x, y + 2 * radius + 3.5f, 7);
                break;
            }
            case 'f':
            {
                pa.circle(x, y, radius);
                break;
            }
            case 'h':
            {
                pa.line(x, y + radius, x, y + 2 * radius);
                pa.circle(x, y + 2 * radius + 3.5f, 7);

                pa.circle(x, y, radius);
                break;
            }
            case 'n':
            {
                break;
            }
        }
    }
}
