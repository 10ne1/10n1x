#
# ~/.bashrc
#

# If not running interactively, don't do anything
[[ $- != *i* ]] && return

source /etc/bash_completion

alias ls='ls --color=auto'
PS1='[\u@\h \W]\$ '
export PATH="$PATH:/sbin:/usr/sbin"
