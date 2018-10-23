ALL: resample_soxr playhrt

cprefresh_ass.o: cprefresh_vfp.s
	$(CC) -c $(CFLAGS) -o cprefresh_ass.o cprefresh_vfp.s

playhrt: playhrt.c cprefresh_ass.o
	$(CC) $(CFLAGS) -o playhrt playhrt.c cprefresh_ass.o $(LDFLAGS) -lasound

resample_soxr: resample_soxr.c cprefresh_ass.o
	$(CC) $(CFLAGS) -o resample_soxr resample_soxr.c cprefresh_ass.o $(LDFLAGS) -lsoxr

clean:
	rm -f resample_soxr playhrt cprefresh_ass.o
