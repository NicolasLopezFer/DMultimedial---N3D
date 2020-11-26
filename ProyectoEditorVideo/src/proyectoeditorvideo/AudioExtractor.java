package proyectoeditorvideo;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.MediaToolAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IAddStreamEvent;
import com.xuggle.mediatool.event.IAudioSamplesEvent;
import com.xuggle.mediatool.event.ICloseEvent;
import com.xuggle.mediatool.event.IOpenCoderEvent;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStreamCoder;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioExtractor {

    public static void convertToMP3(String input, String output) { //modify on your convenience
        File source = new File(input);
        File target = new File(output);

        AudioAttributes audioAttributes = new AudioAttributes();
        audioAttributes.setCodec("libmp3lame");
        audioAttributes.setBitRate(new Integer(128000));
        audioAttributes.setChannels(new Integer(2));
        audioAttributes.setSamplingRate(new Integer(44100));
        EncodingAttributes encodingAttributes = new EncodingAttributes();
        encodingAttributes.setFormat("mp3");
        encodingAttributes.setAudioAttributes(audioAttributes);

        Encoder encoder = new Encoder();
        try { 
            encoder.encode(source, target, encodingAttributes);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AudioExtractor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncoderException ex) {
            Logger.getLogger(AudioExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
