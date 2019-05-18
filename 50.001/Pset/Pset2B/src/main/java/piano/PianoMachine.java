package piano;

import java.util.ArrayList;

import javax.sound.midi.MidiUnavailableException;

import midi.Instrument;
import midi.Midi;
import music.NoteEvent;
import music.Pitch;

public class PianoMachine {
	
	private Midi midi;
	private boolean recording = false;
	private int semitone = 0;
	private Instrument instrument = Midi.DEFAULT_INSTRUMENT;
    private ArrayList<NoteEvent> log = new ArrayList<>();
    private ArrayList<Pitch> playing = new ArrayList<>();


    
	/**
	 * constructor for PianoMachine.
	 * 
	 * initialize midi device and any other state that we're storing.
	 */
    public PianoMachine() {
    	try {
            midi = Midi.getInstance();
        } catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
            return;
        }
    }
    

    public void beginNote(Pitch rawPitch) {
        Pitch nextpitch = rawPitch.transpose(semitone);
        if (!playing.contains(nextpitch)){
            playing.add(nextpitch);
            if (recording){
                log.add(new NoteEvent(nextpitch,System.currentTimeMillis(),instrument,NoteEvent.Kind.start));
            }
            midi.beginNote(nextpitch.toMidiFrequency(), instrument);
        }
    }
    

    public void endNote(Pitch rawPitch) {
        Pitch nextpitch = rawPitch.transpose(semitone);
        if (playing.contains(nextpitch)){
            playing.remove(nextpitch);
            if (recording){
                log.add(new NoteEvent(nextpitch,System.currentTimeMillis(),instrument,NoteEvent.Kind.stop));
            }
            midi.endNote(nextpitch.toMidiFrequency(), instrument);
        }
    }
    

    public void changeInstrument() {
        instrument = instrument.next();
    }

    public void shiftUp() {
        if (semitone < 2*Pitch.OCTAVE){
            semitone += Pitch.OCTAVE;
        }

    }

    public void shiftDown() {
        if (semitone > -2*Pitch.OCTAVE){
            semitone -= Pitch.OCTAVE;
        }
    }
    

    public boolean toggleRecording() {
    	if (recording){
    	    recording = false;
        }
        else {
    	    recording = true;
    	    log.clear();
        }
        return recording;
    }

    public void playback() {
        long delay = 0;

        for (NoteEvent note: log) {
            NoteEvent.Kind notekind = note.getKind();
            if (delay != 0) {
                int duration = Math.round((note.getTime() - delay)/10);
                Midi.rest(duration);
            }

            delay = note.getTime();
            if (notekind == NoteEvent.Kind.start) {
                midi.beginNote(note.getPitch().toMidiFrequency(), note.getInstr());
            } else {
                midi.endNote(note.getPitch().toMidiFrequency(), note.getInstr());
            }
        }
    }


//    public void playback() {
//        long delay = 0;
//        for (int i=0; i<log.size(); i++){
//            NoteEvent note = log.get(i);
//            NoteEvent.Kind notekind = note.getKind();
//
//            if (delay != 0){
//                int duration = Math.round((note.getTime() - delay)/10);
//                Midi.rest(duration);
//            }
//
//            if (notekind == NoteEvent.Kind.start){
//                midi.beginNote(note.getPitch().toMidiFrequency(), note.getInstr());
//            }
//
//            else {
//                midi.endNote(note.getPitch().toMidiFrequency(),note.getInstr());
//            }
//
//        }
//    }

}
