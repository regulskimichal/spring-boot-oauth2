#!/bin/bash
echo "##### REFRESH TOKEN #####"
curl https://localhost:8443/auth/oauth/token -d grant_type=refresh_token -d refresh_token=$3 -u $1:$2 -v -k
echo
echo
