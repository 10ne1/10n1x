#include <stdlib.h>
#include <unistd.h>
#include <getopt.h>
#include <stdio.h>
#include <sys/types.h>
#include <soxr.h>
#include "cprefresh.h"

int main(int argc, char *argv[])
{
  double inrate, outrate, OLEN;
  double *inp, *out;
  int optc;
  long blen, mlen, nch;
  soxr_t soxr;
  soxr_error_t error;
  size_t indone, outdone;

  if (argc == 1) {
      exit(1);
  }

  setbuf(stdout, NULL);
  setbuf(stdin, NULL);
  inrate = 44100.0;
  outrate = 192000.0;
  nch = 2;
  blen = 8192;
  while ((optc = getopt_long(argc, argv, "i:b:", 0, &optind)) != -1) {
      switch (optc) {
      case 'i':
        inrate = atof(optarg);
        break;
      case 'b':
        blen = atoi(optarg);
        if (blen < 1024)
            blen = 8192;
        break;
      default:
        fprintf(stderr, "ERROR: Unknown arg '%c'\n", optc);
        exit(2);
      }
  }

  /* allocate buffer */
  inp = (double*) malloc(nch*blen*sizeof(double));
  OLEN = (long)(blen*(outrate/inrate+1.0));
  out = (double*) malloc(nch*OLEN*sizeof(double));

  /* create resampler for 64 bit floats and high quality */
  soxr_quality_spec_t  q_spec = soxr_quality_spec(0x17, 0);
  q_spec.phase_response = 25.0;
  q_spec.precision = 33.0;
  soxr_io_spec_t const io_spec = soxr_io_spec(SOXR_FLOAT64_I,SOXR_FLOAT64_I);
  soxr_runtime_spec_t const runtime_spec = soxr_runtime_spec(1);
  soxr = soxr_create(inrate, outrate, nch, &error, 
                     &io_spec, &q_spec, &runtime_spec);
  if (error) {
    fprintf(stderr, "resample_soxr: Cannot initialize resampler.\n");
    fflush(stderr);
    exit(1);
  }
     
  while (1) {
    mlen = blen;

    /* read input block */
    memclean((char*)inp, nch*sizeof(double)*mlen);

    mlen = fread((void*)inp, nch*sizeof(double), mlen, stdin);
    if (mlen == 0)
      goto cleanup;

    /* call resampler */
    refreshmem((char*)inp, nch*sizeof(double)*mlen);
    error = soxr_process(soxr, inp, mlen, &indone,
                               out, OLEN, &outdone);
    if (mlen > indone) {
      fprintf(stderr, "resample_soxr: only %ld/%ld processed.\n",(long)indone,(long)mlen);
      fflush(stderr);
    }
    if (error) {
      fprintf(stderr, "resample_soxr: error (%s).\n", soxr_strerror(error));
      fflush(stderr);
      exit(3);
    }
 
    /* write output */
    refreshmem((char*)out, nch*sizeof(double)*outdone);
    fwrite((void*)out, nch*sizeof(double), outdone, stdout);
    memclean((char*)out, nch*sizeof(double)*outdone);
  }

 cleanup:
  soxr_delete(soxr);
  free(inp);
  free(out);
  return(0);
}
