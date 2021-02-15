#!/bin/bash

# Configure GPG
mkdir -p $HOME/.gnupg/
printf "$GPG_SIGNING_KEY" | base64 --decode > $HOME/.gnupg/private.key

[ -f $HOME/.gnupg/private.key ] && echo "$HOME/.gnupg/private.key exists."
echo $GPG_PWD | gpg --batch --yes --passphrase-fd 0 --import $HOME/.gnupg/private.key

echo "Moving GPG files into place"
mv gpg2/gpg-agent.conf $HOME/.gnupg/gpg-agent.conf
mv gpg2/gpg.conf $HOME/.gnupg/gpg.conf

echo "Reloading agent"
gpg-connect-agent reloadagent /bye
