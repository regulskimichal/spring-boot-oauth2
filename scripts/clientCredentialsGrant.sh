#!/bin/bash
echo "##### CLIENT CREDENTIALS GRANT #####"
curl https://localhost:8443/auth/oauth/token -d grant_type=client_credentials -d scope=write -u $1:$2 -v -k
echo
echo
