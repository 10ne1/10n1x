pipe-size: pipe-size.o
	$(CC) $< -o $@

install: pipe-size
	mkdir -p $(DESTDIR)$(PREFIX)/usr/bin
	install -m 0755 $< $(DESTDIR)$(PREFIX)/usr/bin

clean:
	rm -f pipe-size pipe-size.o
