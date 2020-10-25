package app;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MainCountdown {

	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

//		List<String> times = retrieveSunday();
		List<String> times = retrieveMonday();
		
		List<LocalTime> localTimes = times.stream().map(s -> {
			return LocalTime.parse(s);
		}).collect(Collectors.toList()); 
		
		while (true) {
			
			LocalTime treshold = findNextTreshold(localTimes);
			
			System.out.println(treshold);
	        
	        boolean soundPlayed = false;
			
			while (LocalTime.now().isBefore(treshold) ) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				long millis = Duration.between(LocalTime.now(), treshold).toMillis();
				
				String output = String.format("%d minutes %d seconds", 
						  TimeUnit.MILLISECONDS.toMinutes(millis),
						  TimeUnit.MILLISECONDS.toSeconds(millis) - 
						  TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
				
				System.out.println(output + " left to next trip at " + treshold);
				
				if ((millis <= 11000) && !soundPlayed) {
					soundPlayed = true;
					
					Thread thread = new Thread(() -> { play("/Users/bischowg/dev/java/traincountdown/104318211.mp3"); });
					thread.start();
					
				}
			}
		}	
	}

	private static List<String> retrieveMonday() {
		
		List<String> times = new ArrayList<>();
		times.add("04:49:00");
		times.add("05:04:00");
		times.add("05:19:00");
		times.add("05:34:00");
		times.add("05:49:00");
		times.add("06:04:00");
		times.add("06:19:00");
		times.add("06:29:00");
		times.add("06:39:00");
		times.add("06:49:00");
		times.add("06:59:00");
		times.add("07:09:00");
		times.add("07:19:00");
		times.add("07:29:00");
		times.add("07:39:00");
		times.add("07:49:00");
		times.add("07:59:00");
		times.add("08:09:00");
		times.add("08:19:00");
		times.add("08:29:00");
		times.add("08:39:00");
		times.add("08:49:00");
		times.add("08:59:00");
		times.add("09:09:00");
		times.add("09:19:00");
		times.add("09:29:00");
		times.add("09:39:00");
		times.add("09:49:00");
		times.add("09:59:00");
		times.add("10:09:00");
		times.add("10:19:00");
		times.add("10:29:00");
		times.add("10:39:00");
		times.add("10:49:00");
		times.add("10:59:00");
		times.add("11:09:00");
		times.add("11:19:00");
		times.add("11:29:00");
		times.add("11:39:00");
		times.add("11:49:00");
		times.add("11:59:00");
		times.add("12:09:00");
		times.add("12:19:00");
		times.add("12:29:00");
		times.add("12:39:00");
		times.add("12:49:00");
		times.add("12:59:00");
		times.add("13:09:00");
		times.add("13:19:00");
		times.add("13:29:00");
		times.add("13:39:00");
		times.add("13:49:00");
		times.add("13:59:00");
		times.add("14:09:00");
		times.add("14:19:00");
		times.add("14:29:00");
		times.add("14:39:00");
		times.add("14:49:00");
		times.add("14:59:00");
		times.add("15:09:00");
		times.add("15:19:00");
		times.add("15:29:00");
		times.add("15:39:00");
		times.add("15:49:00");
		times.add("15:59:00");
		times.add("16:09:00");
		times.add("16:19:00");
		times.add("16:29:00");
		times.add("16:39:00");
		times.add("16:49:00");
		times.add("16:59:00");
		times.add("17:09:00");
		times.add("17:19:00");
		times.add("17:29:00");
		times.add("17:39:00");
		times.add("17:49:00");
		times.add("17:59:00");
		times.add("18:09:00");
		times.add("18:19:00");
		times.add("18:29:00");
		times.add("18:39:00");
		times.add("18:49:00");
		times.add("18:59:00");
		times.add("19:09:00");
		times.add("19:19:00");
		times.add("19:29:00");
		times.add("19:39:00");
		times.add("19:49:00");
		times.add("19:59:00");
		times.add("20:09:00");
		times.add("20:19:00");
		times.add("20:34:00");
		times.add("20:49:00");
		times.add("21:04:00");
		times.add("21:19:00");
		times.add("21:34:00");
		times.add("21:49:00");
		times.add("22:04:00");
		times.add("22:19:00");
		times.add("22:34:00");
		times.add("22:49:00");
		times.add("23:04:00");
		times.add("23:19:00");
		times.add("23:34:00");
		times.add("23:49:00");
//		times.add("24:04:00");
//		times.add("24:19:00");
//		times.add("24:34:00");
		
		return times;
	}

	@SuppressWarnings("unused")
	private static List<String> retrieveSunday() {
		List<String> times = new ArrayList<>();
		times.add("04:44:00");
		times.add("05:04:00");
		times.add("05:34:00");
		times.add("06:04:00");
		times.add("06:34:00");
		times.add("07:04:00");
		times.add("07:34:00");
		times.add("07:49:00");
		times.add("08:04:00");
		times.add("08:19:00");
		times.add("08:34:00");
		times.add("08:49:00");
		times.add("09:04:00");
		times.add("09:15:00");
		times.add("09:19:00");
		times.add("09:34:00");
		times.add("09:49:00");
		times.add("10:04:00");
		times.add("10:19:00");
		times.add("10:34:00");
		times.add("10:49:00");
		times.add("10:59:00");
		times.add("11:09:00");
		times.add("11:19:00");
		times.add("11:29:00");
		times.add("11:39:00");
		times.add("11:49:00");
		times.add("11:59:00");
		times.add("12:09:00");
		times.add("12:19:00");
		times.add("12:29:00");
		times.add("12:39:00");
		times.add("12:49:00");
		times.add("12:59:00");
		times.add("13:09:00");
		times.add("13:19:00");
		times.add("13:29:00");
		times.add("13:39:00");
		times.add("13:49:00");
		times.add("13:59:00");
		times.add("14:09:00");
		times.add("14:19:00");
		times.add("14:29:00");
		times.add("14:39:00");
		times.add("14:49:00");
		times.add("14:59:00");
		times.add("15:09:00");
		times.add("15:19:00");
		times.add("15:29:00");
		times.add("15:39:00");
		times.add("15:49:00");
		times.add("15:59:00");
		times.add("16:09:00");
		times.add("16:19:00");
		times.add("16:29:00");
		times.add("16:39:00");
		times.add("16:49:00");
		times.add("16:59:00");
		times.add("17:09:00");
		times.add("17:19:00");
		times.add("17:29:00");
		times.add("17:39:00");
		times.add("17:49:00");
		times.add("18:04:00");
		times.add("18:19:00");
		times.add("18:34:00");
		times.add("18:49:00");
		times.add("19:04:00");
		times.add("19:19:00");
		times.add("19:34:00");
		times.add("19:49:00");
		times.add("20:04:00");
		times.add("20:19:00");
		times.add("20:34:00");
		times.add("20:49:00");
		times.add("21:04:00");
		times.add("21:19:00");
		times.add("21:34:00");
		times.add("21:49:00");
		times.add("22:04:00");
		times.add("22:19:00");
		times.add("22:34:00");
		times.add("22:49:00");
		times.add("23:04:00");
		times.add("23:19:00");
		times.add("23:34:00");
		times.add("23:49:00");
//		times.add("24:04:00");
//		times.add("24:19:00");
//		times.add("24:34:00");
		return times;
	}

	private static LocalTime findNextTreshold(List<LocalTime> localTimes) {
		LocalTime now = LocalTime.now();
		LocalTime treshold = null;
		for (LocalTime localTime : localTimes) {
			
			if (now.isBefore(localTime)) {
				treshold = localTime;
				break;
			}
			
		}
		return treshold;
	}
	
	public static void play(String filePath) {
        final File file = new File(filePath);
 
        try (final AudioInputStream in = getAudioInputStream(file)) {
             
            final AudioFormat outFormat = getOutFormat(in.getFormat());
            final Info info = new Info(SourceDataLine.class);
 
            try (final SourceDataLine line =
                     (SourceDataLine) AudioSystem.getLine(info)) {
 
                if (line != null) {
                    line.open(outFormat);
                    line.start();
                    stream(getAudioInputStream(outFormat, in), line);
                    line.drain();
                    line.stop();
                }
            }
 
        } catch (UnsupportedAudioFileException 
               | LineUnavailableException 
               | IOException e) {
            throw new IllegalStateException(e);
        }
    }
 
    private static AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();

        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }
 
    private static void stream(AudioInputStream in, SourceDataLine line) 
        throws IOException {
        final byte[] buffer = new byte[4096];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, n);
        }
    }

}
